package com.insacosa.interfaces;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.degloba.EMF;
import com.google.appengine.api.datastore.Key;

import com.google.appengine.api.datastore.Transaction;
import com.insacosa.dataModels_JPA.PersistenceService;
import com.insacosa.entitats.*;


public class Usuari_Impl implements Usuari_If {


	public Usuari_Impl() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void afegirUsuari(Usuaris usuari) {
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      
				tx.begin();
				
				em.persist(usuari);
				      
				tx.commit();    
		} finally {        
			em.close();    
		} 
		
	}


	public void eliminarUsuari(Key keyUsuari) {
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
				tx.begin();
				
				Usuaris usuari = em.find(Usuaris.class, keyUsuari);
				em.remove(usuari);
     
				tx.commit();    
		} finally {        
			em.close();    
		} 
		
	}


	public Usuaris cercarUsuari(Key keyUsuari) {
		
		Usuaris usuari = null;
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      
				tx.begin();
			
				usuari = em.find(Usuaris.class, keyUsuari);
     
				tx.commit();    
		} finally {        
			em.close();    
		} 
		
		return usuari;
	}


	public Usuaris editPerfil(Key keyUsuari) {
		
		Usuaris usuari = null;
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {   
				tx.begin();
				
				usuari = em.find( Usuaris.class, keyUsuari);
				 
				tx.commit();  
				
		} finally {        
			em.close();    
		}
		return usuari;  
		
	}


	public boolean usuariValid(Usuaris usuari) {
		
		Boolean existeix = false;
		Usuaris existeixUsuari = null;
		
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		
		//La classe PersistenceService es "ApplicationScoped"
		PersistenceService persistenceService = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{persistenceService}", PersistenceService.class);
		
		// Recuperem l'EntityManager
		// 2 Opcions :
		// Opcio 1 --> Utilitzar classe PersistenceService
		EntityManager em = persistenceService.getEntityManager();
		// Opcio 2 --> Utilitzar classe EMF
		em = EMF.get().createEntityManager();
		
		//EntityManager em = EMF.get().createEntityManager();
		//EntityTransaction tx = em.getTransaction();
		
		
		
		try {    
				//tx.begin();
			
			Query q = em.createQuery("select count(usuari) from Usuaris usuari where usuari.nomusuari = '" + usuari.getNomusuari() + "'");
			//q.setHint("datanucleus.query.resultSizeMethod", "count");
			Long num = (Long)q.getSingleResult();
				//existeixUsuari = em.find( Usuaris.class, usuari.getKey());
				
				/*if (existeixUsuari != null)
					existeix = true;
				else
					existeix = false;*/
			if (num == 0)
				existeix = false;
			else
				existeix = true;

				//tx.commit();    
		} finally {        
			//em.close();    
		}
		
		return existeix;
		
		
	}


	public boolean emailValid(String email) {
		
		Long num = null;
		Boolean existeix = false;
		    
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      
	
			tx.begin();
			
			Query q = em.createQuery("select count(*) from Usuaris as usuari where usuari.email = '" + email + "'");
				
			num = (Long)q.getSingleResult();
			if (num == 0)
				existeix = false;
			else
				existeix = true;
				
			tx.commit();    
		} finally {        
			em.close();    
		}
		
		return existeix;

	}

	

	public String passwordEmail(String email) {
		
		String password = null;
		    
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction(); 
		
		try {   
			 tx.begin();
				
			 Query q = em.createQuery(("select password from Usuaris as usuari where usuari.email = '" + email + "'"));
			
			 password = (String)q.getSingleResult();
			 
			 tx.commit();
			
		} finally {        
			em.close();    
		} 
		
		return password;

	}


	

	public String cambiaPassword(Usuaris usuari) {
		   
		EntityManager em = EMF.get().createEntityManager();
		
		EntityTransaction tx = em.getTransaction(); 
		
		try {      
				tx.begin();
				
				em.persist(usuari);
				      
				tx.commit();    
		} finally {        
			em.close();    
		} 
		
		return null;
	}
	




	public String modificarUsuari(Usuaris usuari) {
		
		EntityManager em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {      
				tx.begin();
				
				em.persist(usuari);
				      
				tx.commit();    
		} finally {        
			em.close();    
		}
		return null;
	}


	public String cambiaPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
