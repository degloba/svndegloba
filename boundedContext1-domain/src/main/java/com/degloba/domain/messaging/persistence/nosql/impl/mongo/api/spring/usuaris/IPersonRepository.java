package com.degloba.usuaris.domain.persistence.nosql.mongo.spring;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.degloba.domain.annotations.DomainRepository;
import com.degloba.usuaris.domain.persistence.nosql.mongo.spring.Person;

@DomainRepository
public interface IPersonRepository extends MongoRepository<Person, String> {

	public void logAllPersons();  		
	public void insertPersonWithNameJohnAndRandomAge(); 	
	public void createPersonCollection(); 
	public void dropPersonCollection(); 
	
}
