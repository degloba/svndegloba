package com.insacosa.infraestructura_data_seedwork.Google;


import com.degloba.Util;

//JPA
import javax.persistence.criteria.Predicate;

// Google DataStore
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

import domini.IRepository;

import java.util.List;


/**
 * This class handles all the CRUD operations related to
 * Entitat entity.
 *
 */
public class InsacosaClasseRepository implements IRepository{

  /**
   * Update the Entitat
   * @param name: name of the Entitat
   * @param description : description
   * @return  updated Entitat
   */
  public static void createOrUpdateEntitat(String name, String description) {
    Entity entitat = getEntitat(name);
  	if (entitat == null) {
  		entitat = new Entity("Product", name);
  		entitat.setProperty("description", description);
  	} else {
  		entitat.setProperty("description", description);
  	}
  	Util.persistEntity(entitat);
  }

  /**
   * Return all the Entitats
   * @param kind : of kind Entitat
   * @return  Entitats
   */
  public static Iterable<Entity> getAllEntitats(String kind) {
    return Util.listEntities(kind, null, null);
  }

  /**
   * Get Entitat entity
   * @param name : name of the Entitat
   * @return: Entitat entity
   */
  public static Entity getEntitat(String name) {
  	Key key = KeyFactory.createKey("Product",name);
  	return Util.findEntity(key);
  }

  /**
   * Get all items for a Entitat
   * @param name: name of the Entitat
   * @return list of items
   */
  
  public static List<Entity> getItems(String name) {
  	Query query = new Query();
  	Key parentKey = KeyFactory.createKey("Product", name);
  	query.setAncestor(parentKey);
  	query.addFilter(Entity.KEY_RESERVED_PROPERTY, Query.FilterOperator.GREATER_THAN, parentKey);
  		List<Entity> results = Util.getDatastoreServiceInstance()
  				.prepare(query).asList(FetchOptions.Builder.withDefaults());
  		return results;
	}
  
  /**
   * Delete entitat entity
   * @param EntitatKey: Entitat to be deleted
   * @return status string
   */
  public static String deleteEntitat(String entitatKey)
  {
	  Key key = KeyFactory.createKey("Product",entitatKey);	   
	  
	  List<Entity> items = getItems(entitatKey);	  
	  if (!items.isEmpty()){
	      return "Cannot delete, as there are items associated with this Entitat.";	      
	    }	    
	  Util.deleteEntity(key);
	  return "Entitat deleted successfully";
	  
  }

@Override
public <E> E Find(Predicate predicate) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public <E> E Create(E entitat) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public int Delete(Predicate predicate) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public <E> int Update(E entitat) {
	// TODO Auto-generated method stub
	return 0;
}












}

