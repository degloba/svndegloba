package com.degloba.domain.messaging.persistence.nosql.impl.mongo.api.spring.usuaris;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.degloba.domain.annotations.DomainRepository;
import com.degloba.domain.messaging.persistence.nosql.impl.mongo.api.spring.usuaris.Person;

@DomainRepository
public interface IPersonRepository extends MongoRepository<Person, String> {

	public void logAllPersons();  		
	public void insertPersonWithNameJohnAndRandomAge(); 	
	public void createPersonCollection(); 
	public void dropPersonCollection(); 
	
}
