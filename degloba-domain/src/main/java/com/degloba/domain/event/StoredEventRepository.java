package com.degloba.domain.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
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



/** 
 *  Repository for {@link EventStore}s 
 */ 
@DomainRepositoryImpl
public class StoredEventRepository implements IStoredEventRepository{ 
	static final Logger logger = LoggerFactory.getLogger(StoredEventRepository.class); 

	@Autowired 
	MongoTemplate mongoTemplate; 
	
	private Class<StoredEvent> clazz = null;
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	

	/** 
	 *  Create a {@link EventStore} collection if the collection does not already exists 
	 *  */ 
	
	public void createEventStoreCollection() { 
		if (!mongoTemplate.collectionExists(StoredEvent.class)) { 
			mongoTemplate.createCollection(StoredEvent.class); } } 
	
	/** 
	 * * Drops the {@link EventStore} collection if the collection does already exists 
	 * */ 
	public void dropEventStoreCollection() { 
		if (mongoTemplate.collectionExists(StoredEvent.class)) { 
			mongoTemplate.dropCollection(StoredEvent.class); 
			} 
		}
	
	@Override
	public <S extends StoredEvent> List<S> insert(Iterable<S> entities) {
		return this.insert(entities);
	}
		
	@Override
	public <S extends StoredEvent> List<S> save(Iterable<S> entities) {
		return this.save(entities);
		
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
	public List<StoredEvent> findAll() {		
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
	public List<StoredEvent> findAll(Sort sort) {
		return this.findAll(sort);
		
		/*Query query = new Query().with(sort);
		return mongoTemplate.find(query,Person.class);	*/		
	}
	
	@Override
	public <S extends StoredEvent> S insert(S entity) {	
		return this.insert(entity);			
	}
	
	@Override
	public Page<StoredEvent> findAll(Pageable pageable) {
		return this.findAll(pageable);
	}
	
	@Override
	public <S extends StoredEvent> S save(S entity) {
		return this.save(entity);		
	}
	
	@Override
	public StoredEvent findOne(String id) {
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), clazz) ;
	}
	
	@Override
	public boolean exists(String id) {
		StoredEvent resource = mongoTemplate.findById(new ObjectId(id), clazz);
        clazz = null;
        if(resource == null) return false;
        return true;
	}
	
	@Override
	public Iterable<StoredEvent> findAll(Iterable<String> ids) {
		return this.findAll(ids);
	}
	
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
	
	@Override
	public void delete(String id) {
		this.delete(id);		
	}
	
	@Override
	public void delete(StoredEvent entity) {
		this.delete(entity);		
	}
	
	@Override
	public void delete(Iterable<? extends StoredEvent> entities) {
		this.delete(entities);		
	}
	
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
	public List<StoredEvent> findStoredEventsBetween(Date occurredFrom, Date occurredTo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<StoredEvent> findStoredEventsSince(Date occurredFrom) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public StoredEvent append(DomainEvent domainEvent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public long countStoredEvents() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
