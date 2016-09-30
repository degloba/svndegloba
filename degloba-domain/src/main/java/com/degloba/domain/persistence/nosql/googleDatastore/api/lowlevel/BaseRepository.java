package com.degloba.domain.persistence.nosql.googleDatastore.api.lowlevel;


import java.util.List;

import com.google.cloud.datastore.Key;

public class BaseRepository implements IBaseRepository{

	@Override
	public <T> void create(T t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> String createWithKey(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Long createWithID(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Key create(Key parent, Class<? extends T> kindClass, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> void update(Class<T> clazz, Long id, T t) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void update(Class<T> clazz, Key id, T t) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void update(Class<T> clazz, String key, T t) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T getById(Class<T> clazz, Key id) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getById(Class<T> clazz, String id) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getByKey(Class<T> clazz, String key) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Key getKey(Class<T> clazz, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Key getKey(Key parent, Class<? extends T> kindClass, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> list(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> void delete(Class<T> clazz, Long id) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void delete(Class<T> clazz, Key id) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> void deleteByKey(Class<T> clazz, String key) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
 }
