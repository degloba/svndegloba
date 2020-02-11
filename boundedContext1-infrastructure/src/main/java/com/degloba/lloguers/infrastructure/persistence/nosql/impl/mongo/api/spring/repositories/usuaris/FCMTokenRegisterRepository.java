package com.degloba.lloguers.infrastructure.persistence.nosql.impl.mongo.api.spring.repositories.usuaris;

import java.util.List; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired; 

// Spring Data (mongodb)
import org.springframework.data.mongodb.core.MongoTemplate; 

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.messaging.persistence.nosql.impl.mongo.IFCMTokenRegisterRepository;
import com.degloba.domain.messaging.persistence.nosql.impl.mongo.api.spring.FCM.FCMTokenRegister;


@DomainRepositoryImpl
public class FCMTokenRegisterRepository implements IFCMTokenRegisterRepository{ 
	static final Logger logger = LoggerFactory.getLogger(FCMTokenRegisterRepository.class); 

	@Autowired 
	MongoTemplate mongoTemplate; 
	
	public void logAllFCMTokenRegister() { 
		List<FCMTokenRegister> results = mongoTemplate.findAll(FCMTokenRegister.class); 
		logger.info("Total quantitat of GCMTokenRegister: {}", results.size()); 
		logger.info("Results: {}", results); 
	} 
	
	public void createFCMTokenRegisterCollection() { 
		if (!mongoTemplate.collectionExists(FCMTokenRegister.class)) { 
			mongoTemplate.createCollection(FCMTokenRegister.class); } } 
	
	public void dropFCMTokenRegisterCollection() { 
		if (mongoTemplate.collectionExists(FCMTokenRegister.class)) { 
			mongoTemplate.dropCollection(FCMTokenRegister.class); 
			} 
		}
	
	 public void insertFCMTokenRegister(String token) 
		{ 
			FCMTokenRegister p = new FCMTokenRegister(token); 
			mongoTemplate.insert(p); 
		}
	 
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	} 
	
	

}
