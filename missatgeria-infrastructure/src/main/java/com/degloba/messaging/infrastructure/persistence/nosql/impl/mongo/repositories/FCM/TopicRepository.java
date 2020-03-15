package com.degloba.messaging.infrastructure.persistence.nosql.impl.mongo.repositories.FCM;

import java.util.List; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired; 

import org.springframework.data.mongodb.core.MongoTemplate; 

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.messaging.domain.persistence.nosql.ITopicRepository;
import com.degloba.messaging.domain.persistence.nosql.impl.mongo.api.spring.FCM.Topic;


/** 
 *  @category Repository per {@link Topic}s implementat en MongoDB 
 */ 
@DomainRepositoryImpl
public class TopicRepository implements ITopicRepository{ 
	static final Logger logger = LoggerFactory.getLogger(TopicRepository.class); 

	@Autowired 
	MongoTemplate mongoTemplate; 
	

	/** 
	 *  Create a {@link Person} collection if the collection does not already exists 
	 *  */ 
	
	public void createTopicCollection() { 
		if (!mongoTemplate.collectionExists(Topic.class)) { 
			mongoTemplate.createCollection(Topic.class); } } 
	
	/** 
	 * * Drops the {@link Person} collection if the collection does already exists 
	 * */ 
	public void dropTopicCollection() { 
		if (mongoTemplate.collectionExists(Topic.class)) { 
			mongoTemplate.dropCollection(Topic.class); 
			} 
		}
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public List<Topic> allTopics() {
		return mongoTemplate.findAll(Topic.class); 
	} 

}
