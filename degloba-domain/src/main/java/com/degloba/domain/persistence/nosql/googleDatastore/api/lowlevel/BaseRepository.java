package com.degloba.domain.persistence.nosql.googleDatastore.api.lowlevel;


import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import com.google.cloud.datastore.Batch;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreException;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.google.common.collect.Lists;
import com.google.cloud.datastore.Transaction;

/*
 * https://github.com/GoogleCloudPlatform/google-cloud-java/tree/master/google-cloud-examples
 */
public class BaseRepository implements IBaseRepository{
	
	
	  private final Datastore datastore;
	  
	  private final Transaction transaction;

	  public BaseRepository(Datastore datastore,Transaction transaction) {
	    this.datastore = datastore;
	    this.transaction = transaction;
	  }

	@Override
	public <T> void create(String clazz, String keyName) {
		
		Datastore datastore = DatastoreOptions.defaultInstance().service();
	    KeyFactory keyFactory = datastore.newKeyFactory().kind(clazz);
	    Key key = keyFactory.newKey(keyName);
	    Entity entity = Entity.builder(key)
	       // .set("name", "John Doe")
	       // .set("age", 30)
	       // .set("access_time", DateTime.now())
	        .build();
	    datastore.put(entity);
		
	}


	@Override
	public <T> void update(String clazz, String keyName, String propertyName, String value) throws DatabaseException {
		
		Datastore datastore = DatastoreOptions.defaultInstance().service();
		KeyFactory keyFactory = datastore.newKeyFactory().kind(clazz);
		Key key = keyFactory.newKey(keyName);
		Entity entity = datastore.get(key);
		if (entity != null) {
		      //System.out.println("Updating access_time for " + entity.getString("name"));
		      entity = Entity.builder(entity)
		          .set(propertyName, value)
		          .build();
		      datastore.update(entity);
		}
		
	}


	@Override
	public Entity getById(Class<Entity> clazz, Key id) throws DatabaseException {
		return  this.datastore.get(id);
	}
	
	@Override
	public Entity getEntityWithKey(String clazz, String keyName) {
		 // [START getEntityWithKey]
	    Key key = datastore.newKeyFactory().kind(clazz).newKey(keyName);
	    Entity entity = datastore.get(key);
	    // Do something with the entity
	    // [END getEntityWithKey]
	    return entity;
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
	public <T> void delete(Transaction tx, Key userKey) throws DatabaseException {
		tx.delete(userKey);		
	}
	

	/*@Override
	public String runInTransaction(String callableResult) {
		// [START runInTransaction]
	    TransactionCallable<String> callable = new TransactionCallable<String>() {
	      public String run(DatastoreReaderWriter readerWriter) {
	        // use readerWriter to run in transaction
	        return callableResult;
	      }
	    };
	    String result = datastore.runInTransaction(callable);
	    // [END runInTransaction]
	    return result;
	}*/

	@Override
	public Batch newBatch(String clazz, ArrayList<String> lstKeyString) {
	    // [START newBatch]	   
	    Batch batch = datastore.newBatch();
	    
	    for (String key: lstKeyString) {
	        
		    Entity entity = Entity.builder(datastore.newKeyFactory().kind(clazz).newKey(key))
		    		//.set("name", "John")
		    		.build();
		    
		    batch.add(entity);		    
	    }
	    
	    batch.submit();
	    // [END newBatch]
	    return batch;
	}

	@Override
	public Key allocateIdSingle(String clazz) {
		// [START allocateIdSingle]
	    KeyFactory keyFactory = datastore.newKeyFactory().kind(clazz);
	    IncompleteKey incompleteKey = keyFactory.newKey();

	    // let cloud datastore automatically assign an id
	    Key key = datastore.allocateId(incompleteKey);
	    // [END allocateIdSingle]
	    return key;
	}

	@Override
	public List<Key> batchAllocateId(String clazz) {
		 // [START batchAllocateId]
	    KeyFactory keyFactory = datastore.newKeyFactory().kind(clazz);
	    IncompleteKey incompleteKey1 = keyFactory.newKey();
	    IncompleteKey incompleteKey2 = keyFactory.newKey();

	    // let cloud datastore automatically assign the ids
	    List<Key> keys = datastore.allocateId(incompleteKey1, incompleteKey2);
	    // [END batchAllocateId]
	    return keys;
	}

	@Override
	public void batchUpdateEntities(String clazz, String keyName1, String keyName2) {
		// [START batchUpdateEntities]    
	    Key key1 = datastore.newKeyFactory().kind(clazz).newKey(keyName1);
	    Entity.Builder entityBuilder1 = Entity.builder(key1);
	    entityBuilder1.set("propertyName", "updatedValue1");
	    Entity entity1 = entityBuilder1.build();

	    Key key2 = datastore.newKeyFactory().kind(clazz).newKey(keyName2);
	    Entity.Builder entityBuilder2 = Entity.builder(key2);
	    entityBuilder2.set("propertyName", "updatedValue2");
	    Entity entity2 = entityBuilder2.build();
	    
	    datastore.update(entity1, entity2);
	    // [END batchUpdateEntities]
		
	}

	@Override
	public void putSingleEntity(String clazz, String keyName, String propertyName, String value) {
		 // [START putSingleEntity]
	    Key key = datastore.newKeyFactory().kind(clazz).newKey(keyName);
	    Entity.Builder entityBuilder = Entity.builder(key);
	    entityBuilder.set(propertyName, value);
	    Entity entity = entityBuilder.build();
	    datastore.put(entity);
	    // [END putSingleEntity]
		
	}

	@Override
	public void batchPutEntities(String clazz, String keyName1, String keyName2) {
		 // [START batchPutEntities]    
	    Key key1 = datastore.newKeyFactory().kind(clazz).newKey(keyName1);
	    Entity.Builder entityBuilder1 = Entity.builder(key1);
	    entityBuilder1.set("propertyName", "value1");
	    Entity entity1 = entityBuilder1.build();

	    Key key2 = datastore.newKeyFactory().kind(clazz).newKey(keyName2);
	    Entity.Builder entityBuilder2 = Entity.builder(key2);
	    entityBuilder2.set("propertyName", "value2");
	    Entity entity2 = entityBuilder2.build();

	    datastore.put(entity1, entity2);
	    // [END batchPutEntities]
		
	}

	@Override
	public void batchDeleteEntities(String clazz, String keyName1, String keyName2 ) {
		// [START batchDeleteEntities]   
	    Key key1 = datastore.newKeyFactory().kind(clazz).newKey(keyName1);
	    Key key2 = datastore.newKeyFactory().kind(clazz).newKey(keyName2);
	    datastore.delete(key1, key2);
	    // [END batchDeleteEntities]		
	}

	@Override
	public KeyFactory createKeyFactory() {
		 // [START createKeyFactory]
	    KeyFactory keyFactory = datastore.newKeyFactory();
	    // [END createKeyFactory]
	    return keyFactory;
	}


	@Override
	public List<Entity> getEntitiesWithKeys(String clazz, ArrayList<String> lstKeyString) {
		 // TODO change so that it's not necessary to hold the entities in a list for integration testing
	    // [START getEntitiesWithKeys]
	    KeyFactory keyFactory = datastore.newKeyFactory().kind(clazz);
	    
	    ArrayList<Key> lstKey = new ArrayList<Key>();
	    
	    for (String key: lstKeyString) {
	    	lstKey.add(keyFactory.newKey(key));
	    }
	    	   
	    Iterator<Entity> entitiesIterator = datastore.get(Lists.newArrayList(lstKey));
	    List<Entity> entities = Lists.newArrayList();
	    while (entitiesIterator.hasNext()) {
	      Entity entity = entitiesIterator.next();
	      // do something with the entity
	      entities.add(entity);
	    }
	    // [END getEntitiesWithKeys]
	    return entities;
	}

	@Override
	public List<Entity> fetchEntitiesWithKeys(String clazz, ArrayList<String> lstKeyString) {
		 // [START fetchEntitiesWithKeys]
	    KeyFactory keyFactory = datastore.newKeyFactory().kind(clazz);
	    
	    ArrayList<Key> lstKey = new ArrayList<Key>();
	    
	    for (String key: lstKeyString) {
	    	lstKey.add(keyFactory.newKey(key));
	    }
	    
	    List<Entity> entities = datastore.fetch(Lists.newArrayList(lstKey));
	    for (Entity entity : entities) {
	      // do something with the entity
	    }
	    // [END fetchEntitiesWithKeys]
	    return entities;
	}

	@Override
	public List<Entity> runQuery(String kind) {
		 // TODO change so that it's not necessary to hold the entities in a list for integration testing
	    // [START runQuery]
	    StructuredQuery<Entity> query = Query.entityQueryBuilder()
	        .kind(kind)
	        .build();
	    QueryResults<Entity> results = datastore.run(query);
	    List<Entity> entities = Lists.newArrayList();
	    while (results.hasNext()) {
	      Entity result = results.next();
	      // do something with result
	      entities.add(result);
	    }
	    // [END runQuery]
	    return entities;
	}

	@Override
	public List<Entity> runQueryOnProperty(String kind, String property, String value) {
		 // TODO change so that it's not necessary to hold the entities in a list for integration testing
	    // [START runQueryOnProperty]
	    StructuredQuery<Entity> query = Query.entityQueryBuilder()
	        .kind(kind)
	        .filter(PropertyFilter.eq(property, value))
	        .build();
	    QueryResults<Entity> results = datastore.run(query);
	    List<Entity> entities = Lists.newArrayList();
	    while (results.hasNext()) {
	      Entity result = results.next();
	      // do something with result
	      entities.add(result);
	    }
	    // [END runQueryOnProperty]
	    return entities;
	}

	
	 /**
	   * getting entities for several keys.
	   */
	@Override
	public Entity get(String clazz, String keyName) {
		Datastore datastore = transaction.datastore();
	    // [START get]
	    Key key = datastore.newKeyFactory().kind(clazz).newKey(keyName);
	    Entity entity = transaction.get(key);
	    transaction.commit();
	    // Do something with the entity
	    // [END get]
	    return entity;
	}

	
	/**
	   * fetching a list of entities for several keys.
	   */
	@Override
	public List<Entity> getMultiple(String clazz, String keyName1, String keyName2) {
		Datastore datastore = transaction.datastore();
	    // TODO change so that it's not necessary to hold the entities in a list for integration testing
	    // [START getMultiple]
	    KeyFactory keyFactory = datastore.newKeyFactory().kind(clazz);
	    
	    Key firstKey = keyFactory.newKey(keyName1);
	    Key secondKey = keyFactory.newKey(keyName2);
	    Iterator<Entity> entitiesIterator = transaction.get(firstKey, secondKey);
	    List<Entity> entities = Lists.newArrayList();
	    while (entitiesIterator.hasNext()) {
	      Entity entity = entitiesIterator.next();
	      // do something with the entity
	      entities.add(entity);
	    }
	    transaction.commit();
	    // [END getMultiple]
	    return entities;
	}

	
	/**
	   * running a query to find all entities with an ancestor.
	   */
	@Override
	public List<Entity> run(String parentClazz, String clazz, String parentKeyName) {
		Datastore datastore = transaction.datastore();
	    // [START run]
	    KeyFactory keyFactory = datastore.newKeyFactory().kind(parentClazz);
	    Key parentKey = keyFactory.newKey(parentKeyName);
	    // Build a query
	    Query<Entity> query = Query.entityQueryBuilder()
	        .kind(clazz)
	        .filter(PropertyFilter.hasAncestor(parentKey))
	        .build();
	    QueryResults<Entity> results = transaction.run(query);
	    List<Entity> entities = Lists.newArrayList();
	    while (results.hasNext()) {
	      Entity result = results.next();
	      // do something with result
	      entities.add(result);
	    }
	    transaction.commit();
	    // [END run]
	    return entities;
	}

	 /**
	   * committing a transaction.
	   */
	@Override
	public Key commit(String clazz, String propertyName, String value) {
		 Datastore datastore = transaction.datastore();
		    // [START commit]
		    // create an entity
		    KeyFactory keyFactory = datastore.newKeyFactory().kind(clazz);
		    Key key = datastore.allocateId(keyFactory.newKey());
		    Entity entity = Entity.builder(key)
		    		.set(propertyName, value)
		    		.build();

		    // add the entity and commit
		    try {
		      transaction.put(entity);
		      transaction.commit();
		    } catch (DatastoreException ex) {
		      // handle exception
		    }
		    // [END commit]

		    return key;
	}

	  /**
	   * rolling back a transaction.
	   */
	@Override
	public Key rollback(String clazz, String propertyName, String value) {
		Datastore datastore = transaction.datastore();
	    // [START rollback]
	    // create an entity
	    KeyFactory keyFactory = datastore.newKeyFactory().kind(clazz);
	    Key key = datastore.allocateId(keyFactory.newKey());
	    Entity entity = Entity.builder(key)
	    		.set(propertyName, value)
	    		.build();

	    // add the entity and rollback
	    transaction.put(entity);
	    transaction.rollback();
	    // calling transaction.commit() now would fail
	    // [END rollback]
	    return key;
	}

	/**
	   * verifying if a transaction is active.
	   */
	@Override
	public Key active(String clazz, String propertyName, String value) {
		Datastore datastore = transaction.datastore();
	    // [START active]
	    // create an entity
	    KeyFactory keyFactory = datastore.newKeyFactory().kind(clazz);
	    Key key = datastore.allocateId(keyFactory.newKey());
	    Entity entity = Entity.builder(key)
	    		.set(propertyName, value).build();
	    // calling transaction.active() now would return true
	    try {
	      // add the entity and commit
	      transaction.put(entity);
	      transaction.commit();
	    } finally {
	      // if committing succeeded
	      // then transaction.active() will be false
	      if (transaction.active()) {
	        // otherwise it's true and we need to rollback
	        transaction.rollback();
	      }
	    }
	    // [END active]
	    return key;
	}

	
	/**
	   * how to delete a entity. This action also queries the keys of all "ancestors"
	   * associated with the "entity" and uses them to delete "ancestors".
	   */
	@Override
    public void deleteEntityAndAncestors(Transaction tx, Key key, String kindAncestor) {
      Entity user = tx.get(key);
      if (user == null) {
        System.out.println("Nothing to delete, entity does not exist.");
        return;
      }
      Query<Key> query = Query.keyQueryBuilder()
          //.namespace(NAMESPACE)
          .kind(kindAncestor)
          .filter(PropertyFilter.hasAncestor(key))
          .build();
      QueryResults<Key> ancestors = tx.run(query);
      int count = 0;
      while (ancestors.hasNext()) {
        tx.delete(ancestors.next());
        count++;
      }
      tx.delete(key);
      System.out.printf("Deleting entity '%s' and %d ancestor[s].%n", key.name(), count);
    }
	
	
	
 }
