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


	public Usuaris cercarUsuari(String nomUsuari) {
		// TODO Auto-generated method stub
		
		Usuaris usuari = null;
		
		Transaction tx = datastore.beginTransaction();  	
		
		try {      
	   
				usuari = (Usuaris) session.get(Usuaris.class, nomUsuari);      
				tx.commit();    
				} 
		finally {
			if (tx != null && tx.isActive()) 
			{        
				tx.rollback();        
			}  
		}
		 
		
		return usuari;
	}


	public Usuaris editPerfil(String nomUsuari) {
		// TODO Auto-generated method stub
		
		Usuaris usuari = null;
		
		Transaction tx = datastore.beginTransaction();     

		try {      
    
				usuari = (Usuaris) session.get(Usuaris.class , nomUsuari);  
				
				tx.commit();    
			} 
		finally {
			if (tx != null && tx.isActive()) 
			{        
				tx.rollback();        
			}  
		}
		
		return usuari;  
		
	}


	public boolean usuariValid(Usuaris usuari) {
		// TODO Auto-generated method stub
		  
		Boolean existeix = false;
		
		Transaction tx = datastore.beginTransaction();  
		
		try {      
				if ((Usuaris) session.get(Usuaris.class, usuari.getNomusuari()) != null)
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
		
		Long num = null;
		Boolean existeix = false;
		
		List<Objecte> ret = null;
		
		Transaction tx = datastore.beginTransaction();    
		
		try {      
				
			num = ((Long) session.createQuery("select count(*) from Usuaris as usuari where usuari.email = '" + email + "'").iterate().next() ).longValue();
				
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
		Boolean existeix = false;
		
		List<Objecte> ret = null;
		
		Transaction tx = datastore.beginTransaction();      
		   
		try {      
				
			password = ((String) session.createQuery("select password from Usuaris as usuari where usuari.email = '" + email + "'").iterate().next() ).toString();
			
				
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


	

	public Usuaris password(String nomUsuari)
	{
		Usuaris usuari = null;
		
		Transaction tx = datastore.beginTransaction();     
		
		try {        
				usuari = (Usuaris) session.load(Usuaris.class, nomUsuari);      
				tx.commit();    
			} 
		finally {
			if (tx != null && tx.isActive()) 
			{        
				tx.rollback();        
			}  
		}
			
		return usuari;
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


	@Override
	public void eliminarUsuari(String usuari) {
		// TODO Auto-generated method stub
		
	}

	

}
