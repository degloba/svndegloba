package com.degloba.integration.spring.mongodb.store;

import com.mongodb.MongoClient;
import org.junit.Test;

// Spring Data
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.degloba.integration.spring.mongodb.rules.MongoDbAvailable;

// Spring Integration
import org.springframework.integration.mongodb.store.MongoDbMessageStore;
import org.springframework.integration.store.MessageStore;

/**
 * @author Oleg Zhurakousky
 * @author Gary Russell
 * @author Artem Bilan
 *
 */
public class MongoDbMessageGroupStoreTests extends AbstractMongoDbMessageGroupStoreTests {

	@Override
	protected MongoDbMessageStore getMessageGroupStore() throws Exception {
		MongoDbMessageStore mongoDbMessageStore = new MongoDbMessageStore( new SimpleMongoDbFactory(new MongoClient(), "test"));
		mongoDbMessageStore.afterPropertiesSet();
		return mongoDbMessageStore;
	}

	@Override
	protected MessageStore getMessageStore() throws Exception {
		return this.getMessageGroupStore();
	}

	@Test
	@MongoDbAvailable
	public void testWithAggregatorWithShutdown() throws Exception {
		super.testWithAggregatorWithShutdown("mongo-aggregator-config.xml");
	}

}
