package com.degloba.integration.spring.mongodb.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Test;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.integration.channel.PriorityChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.mongodb.store.ConfigurableMongoDbMessageStore;

import com.degloba.integration.spring.mongodb.rules.MongoDbAvailable;
import org.springframework.integration.store.MessageStore;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.test.util.TestUtils;
import org.springframework.messaging.Message;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * @author Amol Nayak
 * @author Artem Bilan
 *
 */
public class ConfigurableMongoDbMessageGroupStoreTests extends AbstractMongoDbMessageGroupStoreTests {

	@Override
	protected ConfigurableMongoDbMessageStore getMessageGroupStore() throws Exception {
		MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(new MongoClient(), "test");
		ConfigurableMongoDbMessageStore mongoDbMessageStore = new ConfigurableMongoDbMessageStore(mongoDbFactory);
		GenericApplicationContext testApplicationContext = TestUtils.createTestApplicationContext();
		testApplicationContext.refresh();
		mongoDbMessageStore.setApplicationContext(testApplicationContext);
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
		super.testWithAggregatorWithShutdown("mongo-aggregator-confugurable-config.xml");
	}

	@Test
	@MongoDbAvailable
	public void testWithCustomConverter() throws Exception {
		this.cleanupCollections(new SimpleMongoDbFactory(new MongoClient(), "test"));
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("ConfigurableMongoDbMessageStore-CustomConverter.xml", this.getClass());
		context.refresh();

		TestGateway gateway = context.getBean(TestGateway.class);
		String result = gateway.service("foo");
		assertEquals("FOO", result);
		context.close();
	}

	@Test
	@MongoDbAvailable
	public void testPriorityChannel() throws Exception {
		this.cleanupCollections(new SimpleMongoDbFactory(new MongoClient(), "test"));
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("ConfigurableMongoDbMessageStore-CustomConverter.xml", this.getClass());
		context.refresh();

		Object priorityChannel = context.getBean("priorityChannel");
		assertThat(priorityChannel, Matchers.not(Matchers.instanceOf(PriorityChannel.class)));
		assertThat(priorityChannel, Matchers.instanceOf(QueueChannel.class));

		QueueChannel channel = (QueueChannel) priorityChannel;

		Message<String> message = MessageBuilder.withPayload("1").setHeader(IntegrationMessageHeaderAccessor.PRIORITY, 1).build();
		channel.send(message);
		message = MessageBuilder.withPayload("-1").setHeader(IntegrationMessageHeaderAccessor.PRIORITY, -1).build();
		channel.send(message);
		message = MessageBuilder.withPayload("3").setHeader(IntegrationMessageHeaderAccessor.PRIORITY, 3).build();
		channel.send(message);
		message = MessageBuilder.withPayload("0").setHeader(IntegrationMessageHeaderAccessor.PRIORITY, 0).build();
		channel.send(message);
		message = MessageBuilder.withPayload("2").setHeader(IntegrationMessageHeaderAccessor.PRIORITY, 2).build();
		channel.send(message);
		message = MessageBuilder.withPayload("none").build();
		channel.send(message);
		message = MessageBuilder.withPayload("31").setHeader(IntegrationMessageHeaderAccessor.PRIORITY, 3).build();
		channel.send(message);

		Message<?> receive = channel.receive(1000);
		assertNotNull(receive);
		assertEquals("3", receive.getPayload());

		receive = channel.receive(1000);
		assertNotNull(receive);
		assertEquals("31", receive.getPayload());

		receive = channel.receive(1000);
		assertNotNull(receive);
		assertEquals("2", receive.getPayload());

		receive = channel.receive(1000);
		assertNotNull(receive);
		assertEquals("1", receive.getPayload());

		receive = channel.receive(1000);
		assertNotNull(receive);
		assertEquals("0", receive.getPayload());

		receive = channel.receive(1000);
		assertNotNull(receive);
		assertEquals("-1", receive.getPayload());

		receive = channel.receive(1000);
		assertNotNull(receive);
		assertEquals("none", receive.getPayload());

		context.close();
	}



	public static interface TestGateway {

		String service(String payload);

	}

	public static class MessageReadConverter implements Converter<DBObject, Message<?>> {

		@Override
		@SuppressWarnings("unchecked")
		public Message<?> convert(DBObject source) {
			return MessageBuilder.withPayload(source.get("payload")).copyHeaders((Map<String,?>) source.get("headers")).build();
		}

	}

}
