package com.degloba.persistence.nosql.googleDatastore.api.lowlevel;


import java.util.ArrayList;
import java.util.List;

import com.degloba.domain.annotations.DomainRepository;

// Google Datastore
import com.google.cloud.datastore.Batch;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;

import com.google.cloud.datastore.Transaction;

/*
 * Repository d'entitats de domini utilitzant Google Datastore 
 */
@DomainRepository
public interface IBaseRepository {

	public <T> void create(String clazz, String keyName);
	
	public <T> void update(String clazz, String keyName, String propertyName, String value) throws DatabaseException;
			
	public Entity getById(Class<Entity> clazz, Key id) throws DatabaseException;
			
	public <T> Key getKey(Key parent, Class<? extends T> kindClass, long id);
			
	public <T> List<T> list(Class<T> clazz); 

	public <T> void delete(Transaction tx, Key userKey) throws DatabaseException; 
	
	public void deleteEntityAndAncestors(Transaction tx, Key userKey, String kindAncestor);
		
	
	/* A partir d'aqui els mètodes son de : 
	 *  https://github.com/GoogleCloudPlatform/google-cloud-java/blob/master/google-cloud-examples/src/main/java/com/google/cloud/examples/datastore/snippets/DatastoreSnippets.java
	 */
	public String runInTransaction(final String callableResult);
	
	public Batch newBatch(String clazz, ArrayList<String> lstKeyString);
		
	public Key allocateIdSingle(String clazz);
	
	 public List<Key> batchAllocateId(String clazz);
	 
	 public void batchUpdateEntities(String clazz, String keyName1, String keyName2);
	 
	 public void putSingleEntity(String clazz, String keyName, String propertyName, String value);
	 
	 public void batchPutEntities(String clazz, String keyName1, String keyName2);
	 
	 public void batchDeleteEntities(String clazz, String keyName1, String keyName2);
	 
	 public KeyFactory createKeyFactory();
	 
	 public Entity getEntityWithKey(String clazz, String keyName);
	 
	 public List<Entity> getEntitiesWithKeys(String clazz, ArrayList<String> lstKeyString);
		 
	 public List<Entity> fetchEntitiesWithKeys(String clazz, ArrayList<String> lstKeyString);
	 
	 public List<Entity> runQuery(String kind);
	 
	 public List<Entity> runQueryOnProperty(String kind, String property, String value);
	 
	 public Entity get(String clazz, String keyName);
	 
	 public List<Entity> getMultiple(String clazz, String keyName1, String keyName2);
	 
	 public List<Entity> run(String parentClazz, String clazz, String parentKeyName);
	 
	 public Key commit(String clazz, String propertyName, String value);
	 
	 public Key rollback(String clazz, String propertyName, String value);
	 
	 public Key active(String clazz, String propertyName, String value);
	 
	


}
