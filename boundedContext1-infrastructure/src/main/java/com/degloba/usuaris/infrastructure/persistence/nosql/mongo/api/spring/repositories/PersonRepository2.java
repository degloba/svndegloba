package com.degloba.usuaris.infrastructure.persistence.nosql.mongo.api.spring.repositories;


import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;

//Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

// Spring Data (mongodb)
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

// Domain
import com.degloba.domain.annotations.DomainRepositoryImpl;

import com.degloba.usuaris.domain.persistence.nosql.mongo.spring.IPersonRepository2;

//Domain (usuaris)

import com.degloba.usuaris.domain.persistence.nosql.mongo.spring.Person;



/** 
 *  Repository for {@link Person}s 
 */ 
@DomainRepositoryImpl
public class PersonRepository2 implements IPersonRepository2 { 
	static final Logger logger = LoggerFactory.getLogger(PersonRepository2.class); 

	 @Autowired 
	 IPersonRepository2 repository; 
	
	private Class<Person> clazz = null;
	
			
	@Override
	public List<Person> findAll() {		
		return repository.findAll();
		
		/*logger.info("PersonRepository:findAll",clazz);
        if(clazz != null){
            logger.info(Person.class.getName());
            List<Person> list = mongoTemplate.findAll(clazz);
            clazz = null;
            return list;
        } else {
            logger.info("You miss the class!");
            return null;
        }		*/
	}
	
	
	@Override
	public Iterable<Person> findAll(Iterable<String> ids) {
		return this.findAll(ids);
	}
	
	@Override
	public List<Person> findAll(Sort sort) {
		return repository.findAll(sort);
		
		/*Query query = new Query().with(sort);
		return mongoTemplate.find(query,Person.class);	*/		
	}
	
	
	@Override
	public Page<Person> findAll(Pageable pageable) {
		return repository.findAll(pageable);		
	}
	
	
	@Override
	public Person findOne(String id) {
		return repository.findOne(id);
	}
	
	@Override
	public boolean exists(String id) {
		return repository.exists(id);
		/*Person resource = mongoTemplate.findById(new ObjectId(id), clazz);
        clazz = null;
        if(resource == null) return false;*/        
	}
	

	@Override
	public long count() {
		if(clazz != null){
			return repository.count();
            /*Long count = mongoTemplate.count(new Query(), clazz);
            clazz = null;
            return count;*/
        } else {
            return 0;
        }
	}
	
	
	@Override
	public <S extends Person> S save(S entity) {
		return repository.save(entity);				
	}
	
	@Override
	public <S extends Person> List<S> save(Iterable<S> entities) {
		return repository.save(entities);
		
		/*List<S> result = convertIterableToList(entities);
        boolean allNew = true;

        for (S entity : entities) {
            if (allNew ) {
                allNew = false;
            }
        }

        if (allNew) {
            mongoTemplate.insertAll(result);
        } else {

            for (S entity : result) {
                save(entity);
            }
        }

        return result;*/
	}
	
	@Override
	public void delete(String id) {
		repository.delete(id);		
	}
	
	@Override
	public void delete(Person entity) {
		repository.delete(entity);		
	}
	
	@Override
	public void delete(Iterable<? extends Person> entities) {
		repository.delete(entities);		
	}
	
	@Override
	public void deleteAll() {
		repository.deleteAll();		
	} 
	
	@Override
	public <S extends Person> S insert(S entity) {		
		return repository.insert(entity);
	}

	@Override
	public <S extends Person> List<S> insert(Iterable<S> entities) {		
		return repository.insert(entities);
	}
	
	
/*	private static int tryDetermineRealSizeOrReturn(Iterable<?> iterable, int defaultSize) {
	        return iterable == null ? 0 : (iterable instanceof Collection) ? ((Collection<?>) iterable).size() : defaultSize;
	    }*/
	 
/*	private static <T> List<T> convertIterableToList(Iterable<T> entities) {

        if (entities instanceof List) {
            return (List<T>) entities;
        }

        int capacity = tryDetermineRealSizeOrReturn(entities, 10);

        if (capacity == 0 || entities == null) {
            return Collections.<T> emptyList();
        }

        List<T> list = new ArrayList<T>(capacity);
        for (T entity : entities) {
            list.add(entity);
        }

        return list;
    }*/
	
	@Override
	public Iterable<Person> findByLastname(String lastname) {
		return repository.findByLastname(lastname);		
	}
	
	@Override
	public Page<Person> findByFirstname(String firstname, Pageable pageable) {
		return repository.findByFirstname(firstname, pageable);
	}


}
