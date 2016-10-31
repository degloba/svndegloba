package com.degloba.domain.event;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.degloba.domain.annotations.DomainRepository;


@DomainRepository
public interface IEventStoreRepository extends MongoRepository<IEventStore, String> {

/*	public void logAllPersons();  		
	public void insertPersonWithNameJohnAndRandomAge(); 	
	public void createPersonCollection(); 
	public void dropPersonCollection(); */
	
}
