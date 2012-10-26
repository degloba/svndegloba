package com.insacosa.interfaces;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.degloba.EMF;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;



/*
 * Classe que representa els objectes que tenen
 * les propietats de CRUD
 */
public class Objecte implements Interfaces{
	
	//final static Logger logger = LoggerFactory.getLogger(addressI.class);  
	
	private int id;
	
	
	/*
	 * Llegeix un objecte a partir del Id 
	 */
	public Objecte read(Objecte objecte) {
		
		Objecte objecteRead = null;
		Transaction tx = null;    
		EntityManager em = EMF.get().createEntityManager();
		try {     /* 
				      
				objecteRead = (Objecte) session.get(objecte.getClass(), objecte.getId());  
				*/
				tx.commit();    
		} finally {        
			em.close();    
		}		
		return objecteRead;  
		
	}
	
	
	/*
	 * Retorna el max(ID) d'una classe
	 */
	public int retId(String taula, String classe)
	{
		Transaction tx = null;  
		int ret = 0;
		EntityManager em = EMF.get().createEntityManager();
		   
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
			em.close();    
		}		
		
		return ret;
		
	}
	
	
	/*
	 * Retorna la descripcio corresponent a un ID 
	 */
	public Objecte retDescripcio(Class entityName, Key id)
	{
		
		Transaction tx = null;
		Objecte ret = null;
		EntityManager em = EMF.get().createEntityManager();
		   
		try {      
			
			/*
			ret = (Objecte)session.get(entityName , id);
			*/
			tx.commit();    
		} finally {        
			em.close();    
		}		
	
		
	return ret;
	
	}
	
	
	/*
	 * Retorna la llista d'Objectes
	 * Parametres : Classe , Ordre, condicio/criteri 
	 */
	public List<Objecte> llistaObjectes(Class classe, String ordre, String condicio) {
		
		
		List<Objecte> ret = null;
		
		Transaction tx = null;    
		EntityManager em = EMF.get().createEntityManager();
		   
		/*
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
		} finally {        
			em.close();    
		}		
		*/
		
		return ret;
		
	}


	/*
	 * (non-Javadoc)
	 * @see interfaces.Interfaces#delete(interfaces.Objecte)
	 */
	public void delete(Objecte objecte) {
		
		
		Transaction tx = null;    
		EntityManager em = EMF.get().createEntityManager();
		
		try {      
				 /*     
				session.delete(objecte);*/      
				tx.commit();    
		} finally {        
			em.close();    
		}		
		
	}
	

	/*
	 * (non-Javadoc)
	 * @see interfaces.Interfaces#create()
	 */
	public void create() {
		
		
		Transaction tx = null;  
		
		EntityManager em = EMF.get().createEntityManager();
		
		try {   /*   
				      
				session.save(this);
				*/      
				tx.commit();    
		} finally {        
			em.close();    
		}		
					
	}


	/*
	 * (non-Javadoc)
	 * @see interfaces.Interfaces#update(interfaces.Objecte)
	 */
	public void update(Objecte objecte) {
		
		
		
		Transaction tx = null;    
		EntityManager em = EMF.get().createEntityManager();
		try {      
				    /*  
				session.update(objecte);
				*/      
				tx.commit();    
		} finally {        
			em.close();    
		}		 
		
	}


	public List<Objecte> llistaObjectes() {
		
		return null;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
