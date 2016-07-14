package com.degloba;


import java.util.List;

import com.degloba.objectify.DatabaseException;
import com.degloba.objectify.GenericDao;
import com.degloba.rent.domain.objectify.Category;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;



public class AbstractGenericDaoImpl implements GenericDao{

	
	static {
	    ObjectifyService.register(Category.class);
	}
	
	@Override
	public <T> void create(T t) {
		ObjectifyService.ofy().save().entity(t).now();
	}
	
	@Override
	public <T> String createWithKey(T t) {		
	    Key<T> key =  ObjectifyService.ofy().save().entity(t).now();
	    return key.getString();
	}
	
	@Override
	public <T> Long createWithID(T t) {
	    Key<T> key =  ObjectifyService.ofy().save().entity(t).now();
	    return key.getId();
	}
	
	@Override
	public <T> void update(Class<T> clazz, Key<T> id, T t) throws DatabaseException {
	    if (id == null) {
	        throw new DatabaseException("ID cannot be null");
	    }
	    //T tnew = ofy().load().type(clazz).id(id).get();  versio 4
	    T tnew = ObjectifyService.ofy().load().key(id).now();
	    ObjectifyService.ofy().save().entity(tnew).now();
	}
	
	/*@Override
	public <T> void update(Class<T> clazz, String key, T t) throws DatabaseException {
	    if (key == null) {
	        throw new DatabaseException("ID cannot be null");
	    }
	    T tnew = ofy().load().type(clazz).id(key).get();
	    ofy().save().entity(tnew).now();
	
	}*/
	
	@Override
	public <T> T getById(Class<T> clazz, Key<T> id) throws DatabaseException {
	    if (id == null) {
	        throw new DatabaseException("ID cannot be null");
	    }
	    /////return ofy().load().type(clazz).id(id).get();
	    return ObjectifyService.ofy().load().key(id).now();
	}
	
	/*@Override
	public <T> T getByKey(Class<T> clazz, String key) throws DatabaseException {
	    if (key == null) {
	        throw new DatabaseException("ID cannot be null");
	    }
	    return ofy().load().type(clazz).id(key).get();
	}*/
	
	@Override
	public <T> List<T> list(Class<T> clazz) {
	    List<T> list = ObjectifyService.ofy().load().type(clazz).list();
	    return list;
	}
	
	@Override
	public <T> void delete(Class<T> clazz, Key<T> id) throws DatabaseException {
	    if (id == null) {
	        throw new DatabaseException("ID cannot be null");
	    }
	    //////////T t = ObjectifyService.ofy().load().type(clazz).id(id).get();
	    T t = ObjectifyService.ofy().load().key(id).now();
	    if(t != null){
	    	ObjectifyService.ofy().delete().entity(t).now();
	    }
	}

	public AbstractGenericDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*@Override
	public <T> void deleteByKey(Class<T> clazz, String key) throws DatabaseException {
	    if (key == null) {
	        throw new DatabaseException("ID cannot be null");
	    }
	    T t = ofy().load().type(clazz).id(key).get();
	    if(t != null){
	        ofy().delete().entity(t).now();
	    }
	}*/
	
	
 }
