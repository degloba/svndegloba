package com.degloba.gcm;

import java.util.List;

import com.degloba.domain.annotations.DomainRepository;

@DomainRepository
public interface ITopicRepository {

	public List<Topic> allTopics();  			
	public void createTopicCollection(); 
	public void dropTopicCollection(); 
	
}
