package com.degloba.integration.spring.mongodb.config;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

// Spring
import org.springframework.context.support.ClassPathXmlApplicationContext;

// Spring Data
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

import com.degloba.integration.spring.mongodb.rules.MongoDbAvailable;
import com.degloba.integration.spring.mongodb.rules.MongoDbAvailableTests;

// Spring Integration
import org.springframework.integration.support.MessageBuilder;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
/**
 * @author Oleg Zhurakousky
 */
public class MongoDbOutboundChannelAdapterIntegrationTests extends MongoDbAvailableTests {

	@Test
	@MongoDbAvailable
	public void testWithDefaultMongoFactory() throws Exception{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("outbound-adapter-config.xml", this.getClass());

		MessageChannel channel = context.getBean("simpleAdapter", MessageChannel.class);
		Message<Person> message = new GenericMessage<MongoDbAvailableTests.Person>(this.createPerson("Bob"));
		channel.send(message);

		MongoDbFactory mongoDbFactory = this.prepareMongoFactory();
		MongoTemplate template = new MongoTemplate(mongoDbFactory);
		assertNotNull(template.find(new BasicQuery("{'name' : 'Bob'}"), Person.class, "data"));
		context.close();
	}

	@Test
	@MongoDbAvailable
	public void testWithNamedCollection() throws Exception{
		MongoDbFactory mongoDbFactory = this.prepareMongoFactory("foo");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("outbound-adapter-config.xml", this.getClass());

		MessageChannel channel = context.getBean("simpleAdapterWithNamedCollection", MessageChannel.class);
		Message<Person> message = MessageBuilder.withPayload(this.createPerson("Bob")).setHeader("collectionName", "foo").build();
		channel.send(message);

		MongoTemplate template = new MongoTemplate(mongoDbFactory);
		assertNotNull(template.find(new BasicQuery("{'name' : 'Bob'}"), Person.class, "foo"));
		context.close();
	}

	@Test
	@MongoDbAvailable
	public void testWithTemplate() throws Exception{
		MongoDbFactory mongoDbFactory = this.prepareMongoFactory("foo");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("outbound-adapter-config.xml", this.getClass());

		MessageChannel channel = context.getBean("simpleAdapterWithTemplate", MessageChannel.class);
		Message<Person> message = MessageBuilder.withPayload(this.createPerson("Bob")).setHeader("collectionName", "foo").build();
		channel.send(message);

		MongoTemplate template = new MongoTemplate(mongoDbFactory);
		assertNotNull(template.find(new BasicQuery("{'name' : 'Bob'}"), Person.class, "foo"));
		context.close();
	}

	@Test
	@MongoDbAvailable
	public void testSavingDbObject() throws Exception{

		BasicDBObject dbObject = (BasicDBObject) JSON.parse("{'foo' : 'bar'}");

		MongoDbFactory mongoDbFactory = this.prepareMongoFactory("foo");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("outbound-adapter-config.xml", this.getClass());

		MessageChannel channel = context.getBean("simpleAdapterWithTemplate", MessageChannel.class);
		Message<BasicDBObject> message = MessageBuilder.withPayload(dbObject).setHeader("collectionName", "foo").build();
		channel.send(message);

		MongoTemplate template = new MongoTemplate(mongoDbFactory);
		assertNotNull(template.find(new BasicQuery("{'foo' : 'bar'}"), BasicDBObject.class, "foo"));
		context.close();
	}

	@Test
	@MongoDbAvailable
	public void testSavingJSONString() throws Exception{

		String object = "{'foo' : 'bar'}";

		MongoDbFactory mongoDbFactory = this.prepareMongoFactory("foo");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("outbound-adapter-config.xml", this.getClass());

		MessageChannel channel = context.getBean("simpleAdapterWithTemplate", MessageChannel.class);
		Message<String> message = MessageBuilder.withPayload(object).setHeader("collectionName", "foo").build();
		channel.send(message);

		MongoTemplate template = new MongoTemplate(mongoDbFactory);
		assertNotNull(template.find(new BasicQuery("{'foo' : 'bar'}"), BasicDBObject.class, "foo"));
		context.close();
	}

	@Test
	@MongoDbAvailable
	public void testWithMongoConverter() throws Exception{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("outbound-adapter-config.xml", this.getClass());

		MessageChannel channel = context.getBean("simpleAdapterWithConverter", MessageChannel.class);
		Message<Person> message = new GenericMessage<MongoDbAvailableTests.Person>(this.createPerson("Bob"));
		channel.send(message);

		MongoDbFactory mongoDbFactory = this.prepareMongoFactory();
		MongoTemplate template = new MongoTemplate(mongoDbFactory);
		assertNotNull(template.find(new BasicQuery("{'name' : 'Bob'}"), Person.class, "data"));
		context.close();
	}
}
