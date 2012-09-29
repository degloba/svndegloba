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
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
				tx = session.beginTransaction();      
				objecteRead = (Objecte) session.get(objecte.getClass(), objecte.getId());  
				
				tx.commit();    
			} 
		catch (RuntimeException e) 
			{      
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
					catch (HibernateException e1) 
						{          
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
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
		
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
			tx = session.beginTransaction();   
			
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
		catch (RuntimeException e) {      
			if (tx != null && tx.isActive()) 
			{        
				try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
					} 
				catch (HibernateException e1) 
					{          
						System.out.println("Error rolling back transaction");
						//logger.debug("Error rolling back transaction");        
					}
					// throw again the first exception        
					throw e;      
					}    
			}  
		
		return ret;
		
	}
	
	
	/*
	 * Retorna la descripcio corresponent a un ID 
	 */
	public Objecte retDescripcio(Class entityName, int id)
	{
		
		Transaction tx = null;
		Objecte ret = null;

		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
			tx = session.beginTransaction();
			
			ret = (Objecte)session.get(entityName , id);
			
			tx.commit();    
		} 
	catch (RuntimeException e) {      
		if (tx != null && tx.isActive()) 
		{        
			try {
					// Second try catch as the rollback could fail as well          
					tx.rollback();        
				} 
			catch (HibernateException e1) 
				{          
					System.out.println("Error rolling back transaction");
					//logger.debug("Error rolling back transaction");        
				}
				// throw again the first exception        
				throw e;      
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
		
		Transaction tx = null;    

		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
			tx = session.beginTransaction();
			
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
		catch (RuntimeException e) {      
			if (tx != null && tx.isActive()) 
			{        
				try {
					
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
				catch (HibernateException e1) 
					{          
						System.out.println("Error rolling back transaction");
						//logger.debug("Error rolling back transaction");        
					}
					// throw again the first exception        
					throw e;      
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
		
		Transaction tx = null;    
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
				tx = session.beginTransaction();      
				session.delete(objecte);      
				tx.commit();    
			} 
		catch (RuntimeException e) 
			{      
				if (tx != null && tx.isActive()) 
				{        
					try {
							// Second try catch as the rollback could fail as well    
							tx.rollback();        
						} 
					catch (HibernateException e1) 
						{          
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}  
		
	}
	

	/*
	 * (non-Javadoc)
	 * @see interfaces.Interfaces#create()
	 */
	public void create() {
		// TODO Auto-generated method stub
		
		Transaction tx = null;  
		
		SessionFactory sf = SessionFactoryUtil.getInstance();
		Session session = sf.getCurrentSession();
		
		try {      
				tx = session.beginTransaction();      
				session.save(this);      
				tx.commit();    
				} 
		catch (RuntimeException e) 
			{     
			
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
						catch (HibernateException e1) 
						{       
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}  
					
	}


	/*
	 * (non-Javadoc)
	 * @see interfaces.Interfaces#update(interfaces.Objecte)
	 */
	public void update(Objecte objecte) {
		// TODO Auto-generated method stub
		
		
		Transaction tx = null;    
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
				tx = session.beginTransaction();      
				session.update(objecte);      
				tx.commit();    
			} 
			catch (RuntimeException e) 
			{      
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
					catch (HibernateException e1) 
						{          
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
					// throw again the first exception        
					throw e;      
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
