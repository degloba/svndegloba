package com.degloba.gcm.infrastructure.persistence.nosql.mongo.repositories;

import java.util.List; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;

//Spring
import org.springframework.beans.factory.annotation.Autowired; 

// Spring Data (mongodb)
import org.springframework.data.mongodb.core.MongoTemplate; 

// Domain
import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.gcm.domain.persistence.nosql.mongo.spring.ITopicRepository;
import com.degloba.gcm.domain.persistence.nosql.mongo.spring.Topic;

//Domain


/** 
 *  Repository for {@link Topic}s 
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
