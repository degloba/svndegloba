package com.degloba.messaging.domain.persistence.nosql;

import java.util.List;

import com.degloba.domain.annotations.DomainRepository;
import com.degloba.messaging.domain.persistence.nosql.impl.mongo.api.spring.FCM.Topic;

/** 
*
* @category Repositori (MongoDB) : Temes
* 
* @author pere
*
**/ 
@DomainRepository
public interface ITopicRepository {

	public List<Topic> allTopics();  			
	public void createTopicCollection(); 
	public void dropTopicCollection(); 
	
}
