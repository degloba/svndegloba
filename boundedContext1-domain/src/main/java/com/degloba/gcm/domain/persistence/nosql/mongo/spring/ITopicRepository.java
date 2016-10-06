package com.degloba.gcm.domain.persistence.nosql.mongo.spring;

import java.util.List;

import com.degloba.domain.annotations.DomainRepository;

@DomainRepository
public interface ITopicRepository {

	public List<Topic> allTopics();  			
	public void createTopicCollection(); 
	public void dropTopicCollection(); 
	
}