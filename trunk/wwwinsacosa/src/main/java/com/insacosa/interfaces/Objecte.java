package com.insacosa.interfaces;

import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.degloba.EMF;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.QueryResultList;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;
import com.insacosa.dataModels_JPA.PersistenceService;
import com.insacosa.entitats.Ciutats;
import com.insacosa.entitats.Usuaris;



/*
 * Classe que representa els objectes que tenen
 * les propietats de CRUD
 */
public class Objecte implements Interfaces{
	
	//final static Logger logger = LoggerFactory.getLogger(addressI.class);  
	
	private Key id;
	
	static PersistenceService persistenceService;
	
	public Objecte() {
		
		super();
		
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		
		//La classe PersistenceService es "ApplicationScoped"
		persistenceService = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{persistenceService}", PersistenceService.class);
	
	}
	
	/*
	 * Llegeix un objecte a partir del Id 
	 */
	public Objecte read(Objecte objecte) {
		
		Objecte objecteRead = null;
		
		EntityManager em = persistenceService.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {     
			/* 
				      
				objecteRead = (Objecte) session.get(objecte.getClass(), objecte.getId());  
				*/
				tx.commit();    
		} finally {        
			//em.close();    
		}		
		return objecteRead;  
		
	}
	
	
	/*
	 * Retorna el max(ID) d'una classe
	 */
	public int retId(String taula, String classe)
	{

		int ret = 0;
		
		EntityManager em = persistenceService.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		   
		try {      
			   
			/*
			Iterator results = session.createQuery(
					"select max(" + taula + ".id)+1 from " + classe + " as " + taula
			        )
			        .list()
			        .iterator();

			while ( results.hasNext() ) {
			     ret = (Integer) results.next();
			}
			*/

			tx.commit();    
		} finally {        
			//em.close();    
		}		
		
		return ret;
		
	}
	
	
	/*
	 * Retorna la descripcio corresponent a un ID 
	 */
	public Objecte retDescripcio(Class entityName, String id)
	{

		Objecte ret = null;
		
		EntityManager em = persistenceService.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		   
		try {      
			
			/*
			ret = (Objecte)session.get(entityName , id);
			*/
			tx.commit();    
		} finally {        
			//em.close();    
		}		
	
		
	return ret;
	
	}
	
	
	/*
	 * Retorna la llista d'Objectes
	 * Parametres : Classe , Ordre, condicio/criteri 
	 */
	public QueryResultList<Entity> llistaObjectes(Class classe, String ordre, String condicio) {
		
		 QueryResultList<Entity> ret = null;
		
		EntityManager em = persistenceService.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();    
		TransactionOptions options = TransactionOptions.Builder.withXG(true);    
		Transaction txn = datastore.beginTransaction(options);
		
		try {      
			
			if (condicio != "")
			{
				DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
				com.google.appengine.api.datastore.Query q = new com.google.appengine.api.datastore.Query(classe.getSimpleName());
				PreparedQuery pq = ds.prepare(q);
					
				final FetchOptions fetch_options = FetchOptions.Builder.withPrefetchSize(100).chunkSize(100);
				 ret = ds.prepare(q).asQueryResultList(fetch_options);
				
				//Query q = em.createQuery("SELECT c FROM " + classe.getSimpleName() + " c");
				
				//ret =  q.getResultList();
				
			}
			else
			{
				DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
				com.google.appengine.api.datastore.Query q = new com.google.appengine.api.datastore.Query(classe.getSimpleName());
				PreparedQuery pq = ds.prepare(q);
					
				final FetchOptions fetch_options = FetchOptions.Builder.withPrefetchSize(100).chunkSize(100);
				 ret = ds.prepare(q).asQueryResultList(fetch_options);
				 
				//Query q = em.createQuery("SELECT c FROM " + classe.getSimpleName() + " c");
				
			//	ret =  q.getResultList();
			}
			
			txn.commit();    
		} finally {        
			////em.close();    
		}		
		
		
		return ret;
		
	}


	/*
	 * (non-Javadoc)
	 * @see interfaces.Interfaces#delete(interfaces.Objecte)
	 */
	public void delete(Objecte objecte) {
		
		EntityManager em = persistenceService.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {      
				 /*     
				session.delete(objecte);*/      
				tx.commit();    
		} finally {        
			//em.close();    
		}		
		
	}
	

	/*
	 * (non-Javadoc)
	 * @see interfaces.Interfaces#create()
	 */
	public void create() {
		
		EntityManager em = persistenceService.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {   /*   
				      
				session.save(this);
				*/      
				tx.commit();    
		} finally {        
			//em.close();    
		}		
					
	}


	/*
	 * (non-Javadoc)
	 * @see interfaces.Interfaces#update(interfaces.Objecte)
	 */
	public void update(Objecte objecte) {
		
		EntityManager em = persistenceService.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {      
				    /*  
				session.update(objecte);
				*/      
				tx.commit();    
		} finally {        
			//em.close();    
		}		 
		
	}


	public List<Objecte> llistaObjectes() {
		
		return null;
	}

	
	public void setId(Key id) {
		this.id = id;
	}

	public Key getId() {
		return id;
	}

}
