package com.degloba.persistence.nosql.impl.google.datastore.api.objectify.repositories;


import java.util.List;

import com.degloba.domain.annotations.DomainRepository;
import com.degloba.persistence.nosql.impl.google.datastore.api.objectify.exceptions.DatabaseException;
import com.googlecode.objectify.Key;

/**
 * @category Repositori d'entitats de domini utilitzant Google Cloud DataStore
 * 
 * @apiNote Objectify
 * 
 * @author degloba
 *
 */
@DomainRepository
public interface IBaseRepository{

	public <T> void create(T t);
	
	public <T> String createWithKey(T t);

	public <T> Long createWithID(T t);
	
	public <T> Key<T> create(Key<?> parent, Class<? extends T> kindClass, long id);
	
	public <T> void update(Class<T> clazz, Long id, T t) throws DatabaseException;
	
	public <T> void update(Class<T> clazz, Key<T> id, T t) throws DatabaseException;
	
	public <T> void update(Class<T> clazz, String key, T t) throws DatabaseException;
		
	public <T> T getById(Class<T> clazz, Key<T> id) throws DatabaseException;
	
	public <T> T getById(Class<T> clazz, String id) throws DatabaseException;
	
	public <T> T getByKey(Class<T> clazz, String key) throws DatabaseException;
	
	public <T> Key<T> getKey(Class<T> clazz,Long id);
	
	public <T> Key<T> getKey(Key<?> parent, Class<? extends T> kindClass, long id);
			
	public <T> List<T> list(Class<T> clazz); 

	public <T> void delete(Class<T> clazz, Long id) throws DatabaseException; 
	
	public <T> void delete(Class<T> clazz, Key<T> id) throws DatabaseException; 
	
	public <T> void deleteByKey(Class<T> clazz, String key) throws DatabaseException;
		
	public <T> T load(Key<T> id);
		
	public <T> void save(T entitat);
	
	public <T> List<T> getAll(); 
	
}
