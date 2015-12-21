package com.degloba.integration.spring.mongodb.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.springframework.beans.factory.parsing.BeanDefinitionParsingException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.integration.endpoint.SourcePollingChannelAdapter;
import org.springframework.integration.mongodb.inbound.MongoDbMessageSource;
import org.springframework.integration.test.util.TestUtils;


/**
 * @author Oleg Zhurakousky
 */
public class MongoDbInboundChannelAdapterParserTests {

	@Test
	public void minimalConfig(){
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("inbound-adapter-parser-config.xml", this.getClass());
		SourcePollingChannelAdapter spca = context.getBean("minimalConfig.adapter", SourcePollingChannelAdapter.class);
		MongoDbMessageSource source = TestUtils.getPropertyValue(spca, "source", MongoDbMessageSource.class);

		assertEquals(false, TestUtils.getPropertyValue(spca, "shouldTrack"));
		assertNotNull(TestUtils.getPropertyValue(source, "mongoTemplate"));
		assertEquals(context.getBean("mongoDbFactory"), TestUtils.getPropertyValue(source, "mongoDbFactory"));
		assertNotNull(TestUtils.getPropertyValue(source, "evaluationContext"));
		assertTrue(TestUtils.getPropertyValue(source, "collectionNameExpression") instanceof LiteralExpression);
		assertEquals("data", TestUtils.getPropertyValue(source, "collectionNameExpression.literalValue"));
	}

	@Test
	public void fullConfigWithCollectionExpression(){
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("inbound-adapter-parser-config.xml", this.getClass());
		SourcePollingChannelAdapter spca = context.getBean("fullConfigWithCollectionExpression.adapter", SourcePollingChannelAdapter.class);
		MongoDbMessageSource source = TestUtils.getPropertyValue(spca, "source", MongoDbMessageSource.class);

		assertEquals(false, TestUtils.getPropertyValue(spca, "shouldTrack"));
		assertNotNull(TestUtils.getPropertyValue(source, "mongoTemplate"));
		assertEquals(context.getBean("mongoDbFactory"), TestUtils.getPropertyValue(source, "mongoDbFactory"));
		assertEquals(context.getBean("mongoConverter"), TestUtils.getPropertyValue(source, "mongoConverter"));
		assertNotNull(TestUtils.getPropertyValue(source, "evaluationContext"));
		assertTrue(TestUtils.getPropertyValue(source, "collectionNameExpression") instanceof SpelExpression);
		assertEquals("'foo'", TestUtils.getPropertyValue(source, "collectionNameExpression.expression"));
	}

	@Test
	public void fullConfigWithCollectionName(){
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("inbound-adapter-parser-config.xml", this.getClass());
		SourcePollingChannelAdapter spca = context.getBean("fullConfigWithCollectionName.adapter", SourcePollingChannelAdapter.class);
		MongoDbMessageSource source = TestUtils.getPropertyValue(spca, "source", MongoDbMessageSource.class);

		assertEquals(false, TestUtils.getPropertyValue(spca, "shouldTrack"));
		assertNotNull(TestUtils.getPropertyValue(source, "mongoTemplate"));
		assertEquals(context.getBean("mongoDbFactory"), TestUtils.getPropertyValue(source, "mongoDbFactory"));
		assertEquals(context.getBean("mongoConverter"), TestUtils.getPropertyValue(source, "mongoConverter"));
		assertNotNull(TestUtils.getPropertyValue(source, "evaluationContext"));
		assertTrue(TestUtils.getPropertyValue(source, "collectionNameExpression") instanceof LiteralExpression);
		assertEquals("foo", TestUtils.getPropertyValue(source, "collectionNameExpression.literalValue"));
	}

	@Test
	public void fullConfigWithMongoTemplate(){
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("inbound-adapter-parser-config.xml", this.getClass());
		SourcePollingChannelAdapter spca = context.getBean("fullConfigWithMongoTemplate.adapter", SourcePollingChannelAdapter.class);
		MongoDbMessageSource source = TestUtils.getPropertyValue(spca, "source", MongoDbMessageSource.class);

		assertEquals(false, TestUtils.getPropertyValue(spca, "shouldTrack"));
		assertNotNull(TestUtils.getPropertyValue(source, "mongoTemplate"));
		assertEquals(context.getBean("mongoDbTemplate"), TestUtils.getPropertyValue(source, "mongoTemplate"));
		assertNotNull(TestUtils.getPropertyValue(source, "evaluationContext"));
		assertTrue(TestUtils.getPropertyValue(source, "collectionNameExpression") instanceof LiteralExpression);
		assertEquals("foo", TestUtils.getPropertyValue(source, "collectionNameExpression.literalValue"));
	}

	@Test(expected=BeanDefinitionParsingException.class)
	public void templateAndFactoryFail(){
		new ClassPathXmlApplicationContext("inbound-adapter-parser-fail-template-factory-config.xml", this.getClass());
	}

	@Test(expected=BeanDefinitionParsingException.class)
	public void templateAndConverterFail(){
		new ClassPathXmlApplicationContext("inbound-adapter-parser-fail-template-converter-config.xml", this.getClass());
	}
}
