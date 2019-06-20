package com.degloba.integration.spring.mongodb.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.junit.Test;

// Spring 
import org.springframework.beans.factory.parsing.BeanDefinitionParsingException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;

// Spring Data
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;

// Spring Integration
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.endpoint.SourcePollingChannelAdapter;

import com.degloba.integration.spring.mongodb.rules.MongoDbAvailable;
import com.degloba.integration.spring.mongodb.rules.MongoDbAvailableTests;


/**
 * @author Oleg Zhurakousky
 * @since 2.2
 * 
 * @category Testos unitaris de Spring/Integration sobre MongoDB<br/>.
 * S'accedeix a MongoDB de maneres diferents utilitzant el fitexer de configuracio Spring/integration (xml) corresponent
 */
public class MongoDbInboundChannelAdapterIntegrationTests extends MongoDbAvailableTests {

	@Test
	@MongoDbAvailable
	public void testWithDefaultMongoFactory() throws Exception {
		MongoDbFactory mongoDbFactory = this.prepareMongoFactory();
		MongoTemplate template = new MongoTemplate(mongoDbFactory);
		template.save(this.createPerson("Bob"), "data");

		ConfigurableApplicationContext context =
				new ClassPathXmlApplicationContext("inbound-adapter-config.xml", this.getClass());
		SourcePollingChannelAdapter spca = context.getBean("mongoInboundAdapter", SourcePollingChannelAdapter.class);
		QueueChannel replyChannel = context.getBean("replyChannel", QueueChannel.class);
		spca.start();

		@SuppressWarnings("unchecked")
		Message<List<Person>> message = (Message<List<Person>>) replyChannel.receive(10000);
		assertNotNull(message);
		assertEquals("Bob", message.getPayload().get(0).getName());
		assertNotNull(replyChannel.receive(10000));
		context.close();
	}

	@Test
	@MongoDbAvailable
	public void testWithNamedMongoFactory() throws Exception {
		// creem un {@link MongoDbFactory}
		MongoDbFactory mongoDbFactory = this.prepareMongoFactory();
		
		MongoTemplate template = new MongoTemplate(mongoDbFactory);
		template.save(this.createPerson("Bob"), "data");


		// llegim del fitxer de configuració (Spring-Integration)
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("inbound-adapter-config.xml", this.getClass());
		
		// obtenim una instancia (Bean) de l'adaptador d'un {@link QueueChannel}
		SourcePollingChannelAdapter spca = context.getBean("mongoInboundAdapterNamedFactory", SourcePollingChannelAdapter.class);
		
		// obtenim una instancia (Bean) de la cua "replayChannel"
		QueueChannel replyChannel = context.getBean("replyChannel", QueueChannel.class);
		spca.start();

		@SuppressWarnings("unchecked")
		Message<List<DBObject>> message = (Message<List<DBObject>>) replyChannel.receive(10000);
		assertNotNull(message);
		assertEquals("Bob", message.getPayload().get(0).get("name"));
		context.close();
	}

	@Test
	@MongoDbAvailable
	public void testWithMongoTemplate() throws Exception {
		// creem un {@link MongoDbFactory}
		MongoDbFactory mongoDbFactory = this.prepareMongoFactory();
		
		// creem un objecte a la bd MongoDb
		MongoTemplate template = new MongoTemplate(mongoDbFactory);
		template.save(this.createPerson("Bob"), "data");

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("inbound-adapter-config.xml", this.getClass());
		SourcePollingChannelAdapter spca = context.getBean("mongoInboundAdapterWithTemplate", SourcePollingChannelAdapter.class);
		
		// obtenim una instancia (Bean) de la cua "replayChannel"
		QueueChannel replyChannel = context.getBean("replyChannel", QueueChannel.class);
		spca.start();

		@SuppressWarnings("unchecked")
		Message<Person> message = (Message<Person>) replyChannel.receive(10000);
		assertNotNull(message);
		assertEquals("Bob", message.getPayload().getName());
		context.close();
	}

	@Test
	@MongoDbAvailable
	public void testWithNamedCollection() throws Exception {
		// creem un {@link MongoDbFactory}
		MongoDbFactory mongoDbFactory = this.prepareMongoFactory();
		
		// creem un objecte a la bd MongoDb
		MongoTemplate template = new MongoTemplate(mongoDbFactory);
		template.save(this.createPerson("Bob"), "foo");

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("inbound-adapter-config.xml", this.getClass());
		SourcePollingChannelAdapter spca = context.getBean("mongoInboundAdapterWithNamedCollection", SourcePollingChannelAdapter.class);
		QueueChannel replyChannel = context.getBean("replyChannel", QueueChannel.class);
		spca.start();

		@SuppressWarnings("unchecked")
		Message<List<Person>> message = (Message<List<Person>>) replyChannel.receive(10000);
		assertNotNull(message);
		assertEquals("Bob", message.getPayload().get(0).getName());
		context.close();
	}

	@Test
	@MongoDbAvailable
	public void testWithNamedCollectionExpression() throws Exception {
		// creem un {@link MongoDbFactory}
		MongoDbFactory mongoDbFactory = this.prepareMongoFactory();
		
		// creem un objecte a la bd MongoDb
		MongoTemplate template = new MongoTemplate(mongoDbFactory);
		template.save(this.createPerson("Bob"), "foo");

		ConfigurableApplicationContext context =
				new ClassPathXmlApplicationContext("inbound-adapter-config.xml", this.getClass());
		SourcePollingChannelAdapter spca = context.getBean("mongoInboundAdapterWithNamedCollectionExpression",
				SourcePollingChannelAdapter.class);
		QueueChannel replyChannel = context.getBean("replyChannel", QueueChannel.class);
		spca.start();

		@SuppressWarnings("unchecked")
		Message<List<Person>> message = (Message<List<Person>>) replyChannel.receive(10000);
		assertNotNull(message);
		assertEquals("Bob", message.getPayload().get(0).getName());
		context.close();
	}

	@Test
	@MongoDbAvailable
	public void testWithOnSuccessDisposition() throws Exception {
		// creem un {@link MongoDbFactory}
		MongoDbFactory mongoDbFactory = this.prepareMongoFactory();
		
		// creem un objecte a la bd MongoDb
		MongoTemplate template = new MongoTemplate(mongoDbFactory);
		template.save(this.createPerson("Bob"), "data");

		ConfigurableApplicationContext context =
				new ClassPathXmlApplicationContext("inbound-adapter-config.xml", this.getClass());
		SourcePollingChannelAdapter spca = context.getBean("inboundAdapterWithOnSuccessDisposition",
				SourcePollingChannelAdapter.class);
		QueueChannel replyChannel = context.getBean("replyChannel", QueueChannel.class);
		spca.start();

		assertNotNull(replyChannel.receive(10000));
		Thread.sleep(300);
		assertNull(replyChannel.receive(100));
		context.close();
	}

	@Test
	@MongoDbAvailable
	public void testWithMongoConverter() throws Exception {
		// creem un {@link MongoDbFactory}
		MongoDbFactory mongoDbFactory = this.prepareMongoFactory();
		
		// creem un objecte a la bd MongoDb
		MongoTemplate template = new MongoTemplate(mongoDbFactory);
		template.save(this.createPerson("Bob"), "data");

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("inbound-adapter-config.xml", this.getClass());
		SourcePollingChannelAdapter spca = context.getBean("mongoInboundAdapterWithConverter", SourcePollingChannelAdapter.class);
		QueueChannel replyChannel = context.getBean("replyChannel", QueueChannel.class);
		spca.start();

		@SuppressWarnings("unchecked")
		Message<List<Person>> message = (Message<List<Person>>) replyChannel.receive(10000);
		assertNotNull(message);
		assertEquals("Bob", message.getPayload().get(0).getName());
		assertNotNull(replyChannel.receive(10000));
		context.close();
	}

	@Test(expected = BeanDefinitionParsingException.class)
	@MongoDbAvailable
	public void testFailureWithQueryAndQueryExpression() throws Exception {
		new ClassPathXmlApplicationContext("inbound-fail-q-qex.xml", this.getClass()).close();
	}

	@Test(expected = BeanDefinitionParsingException.class)
	@MongoDbAvailable
	public void testFailureWithFactoryAndTemplate() throws Exception {
		new ClassPathXmlApplicationContext("inbound-fail-factory-template.xml", this.getClass()).close();
	}

	@Test(expected = BeanDefinitionParsingException.class)
	@MongoDbAvailable
	public void testFailureWithCollectionAndCollectioinExpression() throws Exception {
		new ClassPathXmlApplicationContext("inbound-fail-c-cex.xml", this.getClass()).close();
	}

	@Test(expected = BeanDefinitionParsingException.class)
	@MongoDbAvailable
	public void testFailureWithTemplateAndConverter() throws Exception {
		new ClassPathXmlApplicationContext("inbound-fail-converter-template.xml", this.getClass()).close();
	}

	public static class DocumentCleaner {

		public void remove(MongoOperations mongoOperations, Object target, String collectionName) {
			if (target instanceof List<?>) {
				List<?> documents = (List<?>) target;
				for (Object document : documents) {
					mongoOperations.remove(new BasicQuery(JSON.serialize(document)), collectionName);
				}
			}
		}

	}

}
