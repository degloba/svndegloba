package com.degloba.integration.spring.mongodb.outbound;

import static org.junit.Assert.assertEquals;

// Mockito
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.bson.conversions.Bson;
import org.mockito.Mockito;

import org.junit.Test;

// Spring
import org.springframework.beans.factory.BeanFactory;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.messaging.Message;

// Spring Data
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;

import com.degloba.integration.spring.mongodb.rules.MongoDbAvailable;
import com.degloba.integration.spring.mongodb.rules.MongoDbAvailableTests;

// Spring Integration
import org.springframework.integration.mongodb.outbound.MongoDbStoringMessageHandler;
import org.springframework.integration.support.MessageBuilder;

import com.mongodb.DBObject;

/**
 * @author Amol Nayak
 * @author Oleg Zhurakousky
 * @author Gary Russell
 *
 * @since 2.2
 */
public class MongoDbStoringMessageHandlerTests extends MongoDbAvailableTests {

	@Test(expected=IllegalArgumentException.class)
	public void withNullMongoDBFactory() {
		new MongoDbStoringMessageHandler((MongoDbFactory)null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void withNullMongoTemplate() {
		new MongoDbStoringMessageHandler((MongoOperations)null);
	}


	@Test
	@MongoDbAvailable
	public void validateMessageHandlingWithDefaultCollection() throws Exception {

		MongoDbFactory mongoDbFactory = this.prepareMongoFactory();
		MongoDbStoringMessageHandler handler = new MongoDbStoringMessageHandler(mongoDbFactory);
		handler.setBeanFactory(mock(BeanFactory.class));
		handler.afterPropertiesSet();
		Message<Person> message = MessageBuilder.withPayload(this.createPerson("Bob")).build();
		handler.handleMessage(message);

		MongoTemplate template = new MongoTemplate(mongoDbFactory);
		Query query = new BasicQuery("{'name' : 'Bob'}");
		Person person = template.findOne(query, Person.class, "data");

		assertEquals("Bob", person.getName());
		assertEquals("PA", person.getAddress().getState());
	}

	@Test
	@MongoDbAvailable
	public void validateMessageHandlingWithNamedCollection() throws Exception {

		MongoDbFactory mongoDbFactory = this.prepareMongoFactory();
		MongoDbStoringMessageHandler handler = new MongoDbStoringMessageHandler(mongoDbFactory);
		handler.setCollectionNameExpression(new LiteralExpression("foo"));
		handler.setBeanFactory(mock(BeanFactory.class));
		handler.afterPropertiesSet();
		Message<Person> message = MessageBuilder.withPayload(this.createPerson("Bob")).build();
		handler.handleMessage(message);

		MongoTemplate template = new MongoTemplate(mongoDbFactory);
		Query query = new BasicQuery("{'name' : 'Bob'}");
		Person person = template.findOne(query, Person.class, "foo");

		assertEquals("Bob", person.getName());
		assertEquals("PA", person.getAddress().getState());
	}

	@Test
	@MongoDbAvailable
	public void validateMessageHandlingWithMongoConverter() throws Exception {

		MongoDbFactory mongoDbFactory = this.prepareMongoFactory();
		MongoDbStoringMessageHandler handler = new MongoDbStoringMessageHandler(mongoDbFactory);
		handler.setCollectionNameExpression(new LiteralExpression("foo"));
		MappingMongoConverter converter = new TestMongoConverter(mongoDbFactory, new MongoMappingContext());
		converter.afterPropertiesSet();
		converter = spy(converter);
		handler.setMongoConverter(converter);
		handler.setBeanFactory(mock(BeanFactory.class));
		handler.afterPropertiesSet();
		Message<Person> message = MessageBuilder.withPayload(this.createPerson("Bob")).build();
		handler.handleMessage(message);

		MongoTemplate template = new MongoTemplate(mongoDbFactory);
		Query query = new BasicQuery("{'name' : 'Bob'}");
		Person person = template.findOne(query, Person.class, "foo");

		assertEquals("Bob", person.getName());
		assertEquals("PA", person.getAddress().getState());
		verify(converter, times(1)).write(Mockito.any(), (Bson) Mockito.any(DBObject.class));
	}

	@Test
	@MongoDbAvailable
	public void validateMessageHandlingWithMongoTemplate() throws Exception {
		MongoDbFactory mongoDbFactory = this.prepareMongoFactory();
		MappingMongoConverter converter = new TestMongoConverter(mongoDbFactory, new MongoMappingContext());
		converter.afterPropertiesSet();
		converter = spy(converter);
		MongoTemplate template = new MongoTemplate(mongoDbFactory, converter);


		MongoDbStoringMessageHandler handler = new MongoDbStoringMessageHandler(template);
		handler.setCollectionNameExpression(new LiteralExpression("foo"));
		handler.setBeanFactory(mock(BeanFactory.class));
		handler.afterPropertiesSet();
		Message<Person> message = MessageBuilder.withPayload(this.createPerson("Bob")).build();
		handler.handleMessage(message);

		MongoTemplate readingTemplate = new MongoTemplate(mongoDbFactory);
		Query query = new BasicQuery("{'name' : 'Bob'}");
		Person person = readingTemplate.findOne(query, Person.class, "foo");

		assertEquals("Bob", person.getName());
		assertEquals("PA", person.getAddress().getState());
		verify(converter, times(1)).write(Mockito.any(), (Bson) Mockito.any(DBObject.class));
	}
}
