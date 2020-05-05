package com.degloba.domain.persistence.nosql.google.datastore.api.lowlevel;

import java.util.ArrayList;
import java.util.List;

import com.degloba.domain.annotations.DomainRepository;
import com.google.cloud.datastore.Batch;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Transaction;


/**
 * @category Repositori d'entitats de domini utilitzant Google Datastore/Natiu 
 */
@DomainRepository
public interface IBaseRepository {

	/**
	 * 
	 * @param clazz
	 * @param keyName
	 * 
	 * @category crea una entitat a partir del nom de la classe i del nom de la clau
	 */
	public <T> void create(String clazz, String keyName);
	
	/**
	 * 
	 * @param clazz
	 * @param keyName
	 * @param propertyName
	 * @param value
	 * @throws DatabaseException
	 * 
	 * @category modifica el valor d'una propietat d'una entitat a partir del nom de la classe,
	 * el nom de la clau i el nom de la propietat
	 */
	public <T> void update(String clazz, String keyName, String propertyName, String value) throws DatabaseException;
			
	/**
	 * 
	 * @param clazz
	 * @param id
	 * @return l'entitat a partir de la {@link Class} i la {@link Key}
	 * @throws DatabaseException
	 * 
	 */
	public Entity getById(Class<Entity> clazz, Key id) throws DatabaseException;
			
	/**
	 * 
	 * @param parent
	 * @param kindClass
	 * @param id
	 * @return
	 */
	public <T> Key getKey(Key parent, Class<? extends T> kindClass, long id);
			
	/**
	 * 
	 * @param clazz
	 * @return la llista d'entitats a partir de la {@link Class}
	 */
	public <T> List<T> list(Class<T> clazz); 

	/**
	 * 
	 * @param tx
	 * @param userKey
	 * @throws DatabaseException
	 * 
	 * @category esborra una entitat a partir dela {@link Key}
	 */
	public <T> void delete(Transaction tx, Key userKey) throws DatabaseException; 
	
	/**
	 * 
	 * @param tx
	 * @param userKey
	 * @param kindAncestor
	 * 
	 * @category esborra una entitat a partir de la {@link Key} i dels seu antecessor
	 */
	public void deleteEntityAndAncestors(Transaction tx, Key userKey, String kindAncestor);
		
	
	/** A partir d'aqui els mètodes son de : 
	 *  https://github.com/GoogleCloudPlatform/google-cloud-java/blob/master/google-cloud-examples/src/main/java/com/google/cloud/examples/datastore/snippets/DatastoreSnippets.java
	 */
	/**
	 * 
	 * @param callableResult
	 * @return
	 * 
	 * @category Executa en una transaccio
	 */
	public String runInTransaction(final String callableResult);
	
	/**
	 * 
	 * @param clazz
	 * @param lstKeyString
	 * @return
	 * 
	 * @category crea un nou Batch
	 */
	public Batch newBatch(String clazz, ArrayList<String> lstKeyString);
		
	/**
	 * 
	 * @param clazz
	 * @return0
	 * 
	 * Reserva una {@link Key}
	 */
	public Key allocateIdSingle(String clazz);
	
	/**
	 * 
	 * @param clazz
	 * @return
	 * 
	 * @category reserva una llista de {@link Key}S en un unic batch
	 */
	 public List<Key> batchAllocateId(String clazz);
	 
	 /**
	  * 
	  * @param clazz
	  * @param keyName1
	  * @param keyName2
	  * 
	  * @category modifica multiples entitats
	  */
	 public void batchUpdateEntities(String clazz, String keyName1, String keyName2);
	 
	 /**
	  * 
	  * @param clazz
	  * @param keyName
	  * @param propertyName
	  * @param value
	  * 
	  * @category afegir una unica entitat
	  */
	 public void putSingleEntity(String clazz, String keyName, String propertyName, String value);
	 
	 /**
	  * 
	  * @param clazz
	  * @param keyName1
	  * @param keyName2
	  * 
	  * @category afegir multiples entitats
	  */
	 public void batchPutEntities(String clazz, String keyName1, String keyName2);
	 
	 /**
	  * 
	  * @param clazz
	  * @param keyName1
	  * @param keyName2
	  * 
	  * @category esborrar multiples entitats
	  */
	 public void batchDeleteEntities(String clazz, String keyName1, String keyName2);
	 
	 /**
	  * 
	  * @return {@code KeyFactory}
	  */
	 public KeyFactory createKeyFactory();
	 
	 /**
	  * 
	  * @param clazz
	  * @param keyName
	  * @return entitat a partir del tipus i el nom de la clau
	  */
	 public Entity getEntityWithKey(String clazz, String keyName);
	 
	 /**
	  * 
	  * @param clazz
	  * @param lstKeyString
	  * @return llista d'entitats d'un tipus a partir d'una llista de claus
	  */
	 public List<Entity> getEntitiesWithKeys(String clazz, ArrayList<String> lstKeyString);
		 
	 /**
	  * 
	  * @param clazz
	  * @param lstKeyString
	  * @return
	  */
	 public List<Entity> fetchEntitiesWithKeys(String clazz, ArrayList<String> lstKeyString);
	 
	 /**
	  * 
	  * @param kind
	  * @return
	  */
	 public List<Entity> runQuery(String kind);
	 
	 
	 public List<Entity> runQueryOnProperty(String kind, String property, String value);
	 
	 public Entity get(String clazz, String keyName);
	 
	 public List<Entity> getMultiple(String clazz, String keyName1, String keyName2);
	 
	 public List<Entity> run(String parentClazz, String clazz, String parentKeyName);
	 
	 public Key commit(String clazz, String propertyName, String value);
	 
	 public Key rollback(String clazz, String propertyName, String value);
	 
	 public Key active(String clazz, String propertyName, String value);
	 
	


}
