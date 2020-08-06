package com.degloba.persistence.mongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;


import com.mongodb.client.MongoClients;
 

// http://www.mkyong.com/mongodb/spring-data-mongodb-hello-world-example/

@Configuration
public class SpringMongoConfig {
	
	
	
// Per arrancar MongoDB en local : 
//	       C:\mongodb-win32-i386-2.4.12\bin>mongod.exe --dbpath d:\mongodb
	
    /*
     * Factory bean that creates the com.mongodb.client.MongoClient instance
     */
     public @Bean MongoClientFactoryBean mongo() {
          MongoClientFactoryBean mongo = new MongoClientFactoryBean();
          mongo.setHost("localhost");
          mongo.setPort(27017);
          
          return mongo;
     }
 
	public @Bean
	SimpleMongoClientDatabaseFactory mongoDbFactory() throws Exception {
		//return new SimpleMongoClientDatabaseFactory(new MongoClient("ds039960.mongolab.com",39960), "degloba");
		return new SimpleMongoClientDatabaseFactory(MongoClients.create(), "test");
	}
 
	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
 
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
 
		return mongoTemplate;
 
	}
 
}
