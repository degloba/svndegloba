package com.degloba.usuaris.infrastructure.persistence.nosql.mongo.api.spring.repositories;

import java.util.List; 

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;

//Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
// Spring Data (mongodb)
import org.springframework.data.mongodb.core.MongoTemplate; 

// Domain
import com.degloba.domain.annotations.DomainRepositoryImpl;

//Domain (usuaris)
import com.degloba.usuaris.domain.IPersonRepository;
import com.degloba.usuaris.domain.persistence.nosql.mongo.spring.Person;



/** 
 *  Repository for {@link Person}s 
 */ 
@DomainRepositoryImpl
public class PersonRepository implements IPersonRepository{ 
	static final Logger logger = LoggerFactory.getLogger(PersonRepository.class); 

	@Autowired 
	MongoTemplate mongoTemplate; 
	
	public void logAllPersons() { 
		List<Person> results = mongoTemplate.findAll(Person.class); 
		logger.info("Total amount of persons: {}", results.size()); 
		logger.info("Results: {}", results); 
	} 
	public void insertPersonWithNameJohnAndRandomAge() 
	{ 
		//get random age between 1 and 100 
		double age = Math.ceil(Math.random() * 100); 
		Person p = new Person("John", (int) age); 
		mongoTemplate.insert(p); 
		} 

	/** 
	 *  Create a {@link Person} collection if the collection does not already exists 
	 *  */ 
	
	public void createPersonCollection() { 
		if (!mongoTemplate.collectionExists(Person.class)) { 
			mongoTemplate.createCollection(Person.class); } } 
	
	/** 
	 * * Drops the {@link Person} collection if the collection does already exists 
	 * */ 
	public void dropPersonCollection() { 
		if (mongoTemplate.collectionExists(Person.class)) { 
			mongoTemplate.dropCollection(Person.class); 
			} 
		}
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	@Override
	public <S extends Person> List<S> save(Iterable<S> entites) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Person> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Person> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Person> S insert(S entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Person> List<S> insert(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page<Person> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Person> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Person findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean exists(String id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Iterable<Person> findAll(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Person entity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Iterable<? extends Person> entities) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	} 
	
	

}
