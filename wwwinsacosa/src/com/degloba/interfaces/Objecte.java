package com.degloba.interfaces;

import java.util.Iterator;
import java.util.List;

//import org.hibernate.Criteria;
//import org.hibernate.Query;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.Session;
//import org.hibernate.HibernateException;
//import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.degloba.HBM.*;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;


/*
 * Classe que representa els objectes que tenen
 * les propietats de CRUD
 */
public class Objecte implements Interfaces{
	
	//final static Logger logger = LoggerFactory.getLogger(addressI.class);  
	
	private int id;
	DatastoreService datastore;
	
	/*
	 * Llegeix un objecte a partir del Id 
	 */
	public Objecte read(Objecte objecte) {
		
		Objecte objecteRead = null;
		
		Transaction tx = datastore.beginTransaction();      
  
		try {      
  
				objecteRead = (Objecte) session.get(objecte.getClass(), objecte.getId());  
				
				tx.commit();    
			} 
		finally {
			if (tx != null && tx.isActive()) 
			{        
				tx.rollback();        
			}  
		}
		
		return objecteRead;  
		
	}
	
	
	/*
	 * Retorna el max(ID) d'una classe
	 */
	public int retId(String taula, String classe)
	{
		 
		int ret = 0;
		
		Transaction tx = datastore.beginTransaction();  
   
		try {      
		   
			
			Iterator results = session.createQuery(
					"select max(" + taula + ".id)+1 from " + classe + " as " + taula
			        )
			        .list()
			        .iterator();

			while ( results.hasNext() ) {
			     ret = (Integer) results.next();
			}
			

			tx.commit();    
			} 
		finally {
			if (tx != null && tx.isActive()) 
			{        
				tx.rollback();        
			}  
		}
				
		return ret;
		
	}
	
	
	/*
	 * Retorna la descripcio corresponent a un ID 
	 */
	public Objecte retDescripcio(Class entityName, int id)
	{

		Objecte ret = null;
		
		Transaction tx = datastore.beginTransaction();  
  
		try {      
			
			ret = (Objecte)session.get(entityName , id);
			
			tx.commit();    
		} 
		finally {

			if (tx != null && tx.isActive()) 
			{        
				tx.rollback();        
			}  
		}
			
	return ret;
	
	}
	
	
	/*
	 * Retorna la llista d'Objectes
	 * Parametres : Classe , Ordre, condicio/criteri 
	 */
	public List<Objecte> llistaObjectes(Class classe, String ordre, String condicio) {
		// TODO Auto-generated method stub
		
		List<Objecte> ret = null;
		
		Transaction tx = datastore.beginTransaction();    
	 
		try {      
						
			if (condicio != "")
			{
				
				Query criteria = session.createQuery(condicio);
				
				ret = criteria.list();
				
				
			}
			else
			{
				Criteria criteria;
				criteria = session.createCriteria(classe)
				.addOrder( Order.asc(ordre));  // class interfaces.Objecte
				
				ret = criteria.list();
			}
			
			tx.commit();    
			} 
		finally {

			if (tx != null && tx.isActive()) 
			{        
				tx.rollback();        
			}  
		}
		
		
		return ret;
		
	}


	/*
	 * (non-Javadoc)
	 * @see interfaces.Interfaces#delete(interfaces.Objecte)
	 */
	public void delete(Objecte objecte) {
		// TODO Auto-generated method stub
		
		Transaction tx = datastore.beginTransaction();     
		   
		try {      
				    
				session.delete(objecte);      
				tx.commit();    
			} 
		finally {

			if (tx != null && tx.isActive()) 
			{        
				tx.rollback();        
			}  
		
		}
		
		
	}
	

	/*
	 * (non-Javadoc)
	 * @see interfaces.Interfaces#create()
	 */
	public void create() {
		// TODO Auto-generated method stub
		
		Transaction tx = datastore.beginTransaction();  
				
		try {      
    
				session.save(this);      
				tx.commit();    
				} 
		finally {
			if (tx != null && tx.isActive()) 
			{        
				tx.rollback();        
			}  
		}
			
	}


	/*
	 * (non-Javadoc)
	 * @see interfaces.Interfaces#update(interfaces.Objecte)
	 */
	public void update(Objecte objecte) {
		// TODO Auto-generated method stub
				
		Transaction tx = datastore.beginTransaction();      
 
		try {     
			
				datastore.put(objecte);      
				tx.commit();    
			} 
		finally {
			if (tx != null && tx.isActive()) 
			{        
				tx.rollback();        
			}  
		}
		
	}


	public List<Objecte> llistaObjectes() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
