package com.degloba;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 

// Spring
import org.springframework.context.ConfigurableApplicationContext; 
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.degloba.lloguers.infrastructure.persistence.nosql.impl.mongo.api.spring.repositories.usuaris.PersonRepository;

// 


/** 
 * Small MongoDB application that uses spring data to interact with MongoDB. 
 */ 
public class MongoDBApp { 
	static final Logger logger = LoggerFactory.getLogger(MongoDBApp.class); 

public static void main( String[] args ) { 
	
	logger.info("Bootstrapping MongoDemo application"); 
	ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/ds-context.xml"); 
	PersonRepository personRepository = context.getBean(PersonRepository.class); 

	// cleanup person collection before insertion 
	personRepository.dropPersonCollection(); 
	
	//create person collection<br /> 
	personRepository.createPersonCollection();
	
	for(int i=0; i<20; i++) { 
		personRepository.insertPersonWithNameJohnAndRandomAge(); 
		} 
	
	personRepository.logAllPersons(); 
	logger.info("Finished MongoDemo application"); 
	} 
} 

