package com.degloba.interfaces;

import java.util.List;

//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.criterion.Order;


import com.degloba.HBM.*;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.files.dev.Session;
import com.degloba.HBM.*;

public class Usuari_Impl implements Usuari_If {

	DatastoreService datastore;
	
	public Usuari_Impl() {
		super();
		// TODO Auto-generated constructor stub
		
		datastore = DatastoreServiceFactory.getDatastoreService();
	}


	public void afegirUsuari(Usuaris usuari) {
		// TODO Auto-generated method stub
		
		Transaction tx = datastore.beginTransaction();
		
		Entity u;
		
		try {
				try {
					u = datastore.get(usuari.getKey());
					datastore.put(u); 
				} catch (EntityNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
     
				tx.commit();    
			} 
		finally {
			if (tx != null && tx.isActive()) 
			{        
				tx.rollback();        
			}  
		}
		
	}


	public void eliminarUsuari(Usuaris usuari) {
		// TODO Auto-generated method stub
		
		Transaction tx = datastore.beginTransaction();
		
		Entity u;
		
		try {      
				datastore.delete(usuari.getKey()); 
							      
				tx.commit();    
			} 
		finally {
			if (tx != null && tx.isActive()) 
			{        
				tx.rollback();        
			}  
		}
		
		
	}


	public Entity cercarUsuari(String nomUsuari) {
		// TODO Auto-generated method stub
		
		Entity u ;
		
		Transaction tx = datastore.beginTransaction();  	
		
		try {      
				Query q = new Query("Usuaris");
				q.addFilter("nomusuari", Query.FilterOperator.EQUAL, nomUsuari);
				PreparedQuery pq = datastore.prepare(q);
				
				u = pq.asSingleEntity();
								     
				tx.commit();    
				} 
		finally {
			if (tx != null && tx.isActive()) 
			{        
				tx.rollback();        
			}  
		}
		 
		
		return u;
	}


	public Entity editPerfil(String nomUsuari) {
		// TODO Auto-generated method stub
		
		Entity u ;
		
		Transaction tx = datastore.beginTransaction();     

		try {      
				Query q = new Query("Usuaris");
				q.addFilter("nomusuari", Query.FilterOperator.EQUAL, nomUsuari);
				PreparedQuery pq = datastore.prepare(q);
				
				u = pq.asSingleEntity();
				
				tx.commit();    
			} 
		finally {
			if (tx != null && tx.isActive()) 
			{        
				tx.rollback();        
			}  
		}
		
		return u;  
		
	}


	public boolean usuariValid(Usuaris usuari) {
		// TODO Auto-generated method stub
		  
		Boolean existeix = false;
		
		Entity u ;
		
		Transaction tx = datastore.beginTransaction();  
		
		try {      
			
				Query q = new Query("Usuaris");
				q.addFilter("nomusuari", Query.FilterOperator.EQUAL, usuari.getNomusuari());
				PreparedQuery pq = datastore.prepare(q);
				
				u = pq.asSingleEntity();
			
				if (u != null)
					existeix = true;
				else
					existeix = false;
				
				tx.commit();    
			} 
		finally {
			if (tx != null && tx.isActive()) 
			{        
				tx.rollback();        
			}  
		}
		
		
		return existeix;
		
		
	}


	public boolean emailValid(String email) {
		// TODO Auto-generated method stub
		
		Integer num = null;
		Boolean existeix = false;
		
		Transaction tx = datastore.beginTransaction();    
		
		try {   
				Query q = new Query("Usuaris");
				q.addFilter("email", Query.FilterOperator.EQUAL, email);
				PreparedQuery pq = datastore.prepare(q);
				num = pq.asList(null).size();
				
				if (num == 0)
					existeix = false;
				else
					existeix = true;
					
				tx.commit();    
			} 
		finally {
			if (tx != null && tx.isActive()) 
			{        
				tx.rollback();        
			}  
		}
		
		
		return existeix;

	}

	

	public String passwordEmail(String email) {
		// TODO Auto-generated method stub
		
		String password = null;
		
		Transaction tx = datastore.beginTransaction();      
		   
		try {      
				
				Query q = new Query("Usuaris");
				q.addFilter("email", Query.FilterOperator.EQUAL, email);
				PreparedQuery pq = datastore.prepare(q);
				
				Entity result = pq.asSingleEntity();
				
				password = (String) result.getProperty("password");
								
				tx.commit();    
			} 
		finally {
			if (tx != null && tx.isActive()) 
			{        
				tx.rollback();        
			}  
		}
		
		return password;

	}


	

	public String cambiaPassword(Usuaris usuari) {
		// TODO Auto-generated method stub
		
		
		Transaction tx = datastore.beginTransaction();     
		
		Entity p;
		try {    
				try {
					p = datastore.get(usuari.getKey());
					datastore.put(p); 
				} catch (EntityNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					     
				tx.commit();    
			} 
		finally {
			if (tx != null && tx.isActive()) 
			{        
				tx.rollback();        
			}  
		}
		
		return null;
	}


	

	public Entity password(String nomUsuari)
	{
	
		Entity u;
		
		Transaction tx = datastore.beginTransaction();     
		
		try {        
				Query q = new Query("Usuaris");
				q.addFilter("nomusuari", Query.FilterOperator.EQUAL, nomUsuari);
				PreparedQuery pq = datastore.prepare(q);
			
				u = pq.asSingleEntity();
    
				tx.commit();    
			} 
		finally {
			if (tx != null && tx.isActive()) 
			{        
				tx.rollback();        
			}  
		}
			
		return u;
	}


	@Override
	public String cambiaPassword() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String modificarUsuari(Usuaris usuari) {
		// TODO Auto-generated method stub´
		
		Transaction tx = datastore.beginTransaction();   
			
		
		try {	  
				Entity u;
				try {
					u = datastore.get(usuari.getKey());
					datastore.put(u); 
				} catch (EntityNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
     
				tx.commit();    
				} 
		finally {
			if (tx != null && tx.isActive()) 
			{        
				tx.rollback();        
			}  
		}
		
		return null;
	}


}
