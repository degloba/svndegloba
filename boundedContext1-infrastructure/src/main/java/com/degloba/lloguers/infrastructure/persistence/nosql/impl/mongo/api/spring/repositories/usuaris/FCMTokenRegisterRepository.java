package com.degloba.usuaris.infrastructure.persistence.nosql.mongo.api.spring.repositories;

import java.util.List; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;

//Spring
import org.springframework.beans.factory.annotation.Autowired; 

// Spring Data (mongodb)
import org.springframework.data.mongodb.core.MongoTemplate; 

// Domain
import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.gcm.domain.persistence.nosql.mongo.spring.GCMTokenRegister;
import com.degloba.gcm.domain.persistence.nosql.mongo.spring.IGCMTokenRegisterRepository;


@DomainRepositoryImpl
public class GCMTokenRegisterRepository implements IGCMTokenRegisterRepository{ 
	static final Logger logger = LoggerFactory.getLogger(GCMTokenRegisterRepository.class); 

	@Autowired 
	MongoTemplate mongoTemplate; 
	
	public void logAllGCMTokenRegister() { 
		List<GCMTokenRegister> results = mongoTemplate.findAll(GCMTokenRegister.class); 
		logger.info("Total quantitat of GCMTokenRegister: {}", results.size()); 
		logger.info("Results: {}", results); 
	} 
	
	public void createGCMTokenRegisterCollection() { 
		if (!mongoTemplate.collectionExists(GCMTokenRegister.class)) { 
			mongoTemplate.createCollection(GCMTokenRegister.class); } } 
	
	public void dropGCMTokenRegisterCollection() { 
		if (mongoTemplate.collectionExists(GCMTokenRegister.class)) { 
			mongoTemplate.dropCollection(GCMTokenRegister.class); 
			} 
		}
	
	 public void insertGCMTokenRegister(String token) 
		{ 
			GCMTokenRegister p = new GCMTokenRegister(token); 
			mongoTemplate.insert(p); 
		}
	 
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	} 
	
	

}
