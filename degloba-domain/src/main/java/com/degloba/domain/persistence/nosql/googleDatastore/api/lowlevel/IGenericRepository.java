package com.degloba.domain.persistence.nosql.googleDatastore.api.lowlevel;

import java.util.List;

import com.degloba.domain.annotations.DomainRepository;
//import com.google.appengine.api.datastore.Key;
import com.google.cloud.datastore.Key;


@DomainRepository
public interface IGenericRepository  {
	
	public <T> void create(T t);
	
	public <T> String createWithKey(T t); 

	public <T> Long createWithID(T t); 
	
	public <T> void update(Class<T> clazz, Key id, T t) throws DatabaseException;
	
	public <T> T getById(Class<T> clazz, Key id) throws DatabaseException;
	
	public <T> List<T> list(Class<T> clazz); 
	
	public <T> void delete(Class<T> clazz, Key id) throws DatabaseException; 
		

}
