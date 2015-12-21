
package com.degloba.integration.spring.mongodb.store;

import com.mongodb.MongoClient;

import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.integration.mongodb.store.MongoDbMessageStore;
import org.springframework.integration.store.MessageStore;

/**
 * @author Mark Fisher
 * @author Oleg Zhurakousky
 * @author Artem Bilan
 *
 */
public class MongoDbMessageStoreTests extends AbstractMongoDbMessageStoreTests {

	@Override
	protected MessageStore getMessageStore() throws Exception {
		MongoDbMessageStore mongoDbMessageStore = new MongoDbMessageStore(new SimpleMongoDbFactory(new MongoClient(), "test"));
		mongoDbMessageStore.afterPropertiesSet();
		return mongoDbMessageStore;
	}

}
