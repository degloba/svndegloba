package com.degloba.domain.persistence.nosql.googleDatastore.api.lowlevel;


import java.util.List;

import com.degloba.domain.annotations.DomainRepository;


import com.google.cloud.datastore.Batch;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.Datastore.TransactionCallable;
import com.google.cloud.datastore.DatastoreReaderWriter;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery;
import com.google.cloud.datastore.Transaction;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.google.common.collect.Lists;

@DomainRepository
public interface IBaseRepository {

	public <T> void create(String clazz, String keyName);
	
	public <T> void update(String clazz, String keyName) throws DatabaseException;
			
	public <T> T getById(Class<T> clazz, Key id) throws DatabaseException;
	
	public <T> T getById(Class<T> clazz, String id) throws DatabaseException;
	
	public <T> T getByKey(Class<T> clazz, String key) throws DatabaseException;
	
	public <T> Key getKey(Class<T> clazz,Long id);
	
	public <T> Key getKey(Key parent, Class<? extends T> kindClass, long id);
			
	public <T> List<T> list(Class<T> clazz); 

	public <T> void delete(Transaction tx, Key userKey) throws DatabaseException; 
		
	
	/* A partir d'aqui els mètodes son de : 
	 *  https://github.com/GoogleCloudPlatform/google-cloud-java/blob/master/google-cloud-examples/src/main/java/com/google/cloud/examples/datastore/snippets/DatastoreSnippets.java
	 */
	/////public String runInTransaction(final String callableResult);
	
	public Batch newBatch(String clazz,String keyName1, String keyName2);
		
	public Key allocateIdSingle(String clazz);
	
	 public List<Key> batchAllocateId(String clazz);
	 
	 public void batchUpdateEntities(String clazz, String keyName1, String keyName2);
	 
	 public void putSingleEntity(String clazz, String keyName);
	 
	 public void batchPutEntities(String clazz, String keyName1, String keyName2);
	 
	 public void batchDeleteEntities(String clazz, String keyName1, String keyName2);
	 
	 public KeyFactory createKeyFactory();
	 
	 public Entity getEntityWithKey(String clazz, String keyName);
	 
	 public List<Entity> getEntitiesWithKeys(String clazz, String firstKeyName, String secondKeyName);
		 
	 public List<Entity> fetchEntitiesWithKeys(String clazz, String firstKeyName, String secondKeyName);
	 
	 public List<Entity> runQuery(String kind);
	 
	 public List<Entity> runQueryOnProperty(String kind, String property, String value);
	 
	 public Entity get(String clazz, String keyName);
	 
	 public List<Entity> getMultiple(String clazz, String firstKeyName, String secondKeyName);
	 
	 public List<Entity> run(String parentClazz, String clazz, String parentKeyName);
	 
	 public Key commit(String clazz);
	 
	 public Key rollback(String clazz);
	 
	 public Key active(String clazz);
	 
	


}
