package com.degloba.JPA;

import java.util.Map;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public  class EMF {  

	private static final EntityManagerFactory emfInstance =  Persistence.createEntityManagerFactory("transactions-optional", new Properties());      
	
	private EMF() {}
	
public static EntityManagerFactory get() {    
	
	return emfInstance;    
	}


	/*--------------------------*/
	/* EntityManager - JPA      */
	/*--------------------------*/
	 public static EntityManager lookupEntityManager() {         
		 
    	// CUIDADO !!!!!! el #{persistenceService} no s'ha de cambiar encara que es canvii de persistence-unit
	    //PersistenceService persistenceService = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{persistenceService}", PersistenceService.class);
        Map<Object, Object> attributes = FacesContext.getCurrentInstance().getAttributes();
	        
	    EntityManager manager = (EntityManager) attributes.get(PersistenceService.class);
		 
		 if (manager == null) {
	            manager = emfInstance.createEntityManager();
	            attributes.put(PersistenceService.class, manager);
         }
		 
	 return manager;     
	 } 


}
