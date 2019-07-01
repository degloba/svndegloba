package com.degloba.usuaris.infrastructure.persistence.nosql.mongo.api.spring.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;

//Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

// Spring Data (mongodb)
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

// Domain
import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.usuaris.domain.persistence.nosql.mongo.spring.IPersonRepository;

//Domain (usuaris)

import com.degloba.usuaris.domain.persistence.nosql.mongo.spring.Person;


/** 
 *  @category Repositori + NoSQL : {@link Person}s 
 */ 
@DomainRepositoryImpl
public class PersonRepository implements IPersonRepository{ 
	static final Logger logger = LoggerFactory.getLogger(PersonRepository.class); 

	@Autowired 
	MongoTemplate mongoTemplate; 
	
	private Class<Person> clazz = null;
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public void logAllPersons() { 
		List<Person> results = mongoTemplate.findAll(Person.class); 
		logger.info("Total quantitat of persons: {}", results.size()); 
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
	
	@Override
	public <S extends Person> List<S> insert(Iterable<S> entities) {
		return this.insert(entities);
	}
		
	/*@Override
	public <S extends Person> List<S> save(Iterable<S> entities) {
		return this.save(entities);
		
		List<S> result = convertIterableToList(entities);
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

        return result;
	}*/
	
	@Override
	public List<Person> findAll() {		
		return this.findAll();
		
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
	public List<Person> findAll(Sort sort) {
		return this.findAll(sort);
		
		/*Query query = new Query().with(sort);
		return mongoTemplate.find(query,Person.class);	*/		
	}
	
	@Override
	public <S extends Person> S insert(S entity) {	
		return this.insert(entity);			
	}
	
	@Override
	public Page<Person> findAll(Pageable pageable) {
		return this.findAll(pageable);
	}
	
	@Override
	public <S extends Person> S save(S entity) {
		return this.save(entity);		
	}
	
	/*@Override
	public Person findOne(String id) {
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), clazz) ;
	}
	
	@Override
	public boolean exists(String id) {
		Person resource = mongoTemplate.findById(new ObjectId(id), clazz);
        clazz = null;
        if(resource == null) return false;
        return true;
	}
	
	@Override
	public Iterable<Person> findAll(Iterable<String> ids) {
		return this.findAll(ids);
	}*/
	
	@Override
	public long count() {
		if(clazz != null){
            Long count = mongoTemplate.count(new Query(), clazz);
            clazz = null;
            return count;
        } else {
            return 0;
        }
	}
	
	/*@Override
	public void delete(String id) {
		this.delete(id);		
	}*/
	
	@Override
	public void delete(Person entity) {
		this.delete(entity);		
	}
	
	/*@Override
	public void delete(Iterable<? extends Person> entities) {
		this.delete(entities);		
	}*/
	
	@Override
	public void deleteAll() {
		this.deleteAll();		
	} 
	
	
	private static int tryDetermineRealSizeOrReturn(Iterable<?> iterable, int defaultSize) {
	        return iterable == null ? 0 : (iterable instanceof Collection) ? ((Collection<?>) iterable).size() : defaultSize;
	    }
	 
	private static <T> List<T> convertIterableToList(Iterable<T> entities) {

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
    }
	@Override
	public <S extends Person> List<S> saveAll(Iterable<S> entities) {
		List<S> result = convertIterableToList(entities);
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

        return result;
	}

	@Override
	public Optional<Person> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Iterable<Person> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAll(Iterable<? extends Person> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Person> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Person> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Person> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public <S extends Person> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public <S extends Person> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <S extends Person> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
