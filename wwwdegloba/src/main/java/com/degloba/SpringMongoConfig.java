package com.degloba;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
 
import com.mongodb.MongoClient;
 

// http://www.mkyong.com/mongodb/spring-data-mongodb-hello-world-example/

@Configuration
public class SpringMongoConfig {
 
	public @Bean
	MongoDbFactory mongoDbFactory() throws Exception {
		//return new SimpleMongoDbFactory(new MongoClient("ds039960.mongolab.com",39960), "degloba");
		return new SimpleMongoDbFactory(new MongoClient("localhost",27017), "test");
	}
 
	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
 
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
 
		return mongoTemplate;
 
	}
 
}
