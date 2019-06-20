package com.degloba.integration.spring.mongodb.rules;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

/**
 * Una implementació d'un {@link MethodRule} que comprova un procés MongoDB en execució.
 *
 * @author Oleg Zhurakousky
 * @author Gary Russell
 * @since 2.1
 */
public final class MongoDbAvailableRule implements MethodRule {

	private final Log logger = LogFactory.getLog(this.getClass());

	@Override
	public Statement apply(final Statement base, final FrameworkMethod method, final Object target) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				MongoDbAvailable mongoAvailable = method.getAnnotation(MongoDbAvailable.class);
				if (mongoAvailable != null) {
					try {
						MongoClientOptions options = new MongoClientOptions.Builder().connectTimeout(100).build();
						Mongo mongo = new MongoClient(ServerAddress.defaultHost(), options);
						mongo.getDatabaseNames();
					}
					catch (Exception e) {
						logger.warn("MongoDb is not available. Skipping the test: " +
								target.getClass().getSimpleName() + "." + method.getName() + "()");
						return;
					}
				}
				base.evaluate();
			}
		};
	}

}
