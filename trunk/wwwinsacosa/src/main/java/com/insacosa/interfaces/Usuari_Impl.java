package com.insacosa.interfaces;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.degloba.EMF;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

import com.google.appengine.api.datastore.Transaction;
import com.insacosa.dataModels_JPA.PersistenceService;
import com.insacosa.entitats.*;


public class Usuari_Impl implements Usuari_If {

	static PersistenceService persistenceService;
	
	public Usuari_Impl() {
		super();
		// TODO Auto-generated constructor stub
		
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		
		//La classe PersistenceService es "ApplicationScoped"
		persistenceService = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{persistenceService}", PersistenceService.class);
		
	}


	public void afegirUsuari(Usuaris usuari) {
			
		// Recuperem l'EntityManager
		// 2 Opcions :
		// Opcio 1 --> Utilitzar classe PersistenceService
		EntityManager em = persistenceService.getEntityManager();
		// Opcio 2 --> Utilitzar classe EMF
		//em = EMF.get().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Usuaris u = new Usuaris();

		u.setNomusuari(usuari.getNomusuari());
		u.setNom(usuari.getNom());
		u.setCognoms(usuari.getCognoms());
		u.setAdreca(usuari.getAdreca());
		u.setCodi(usuari.getCodi());
		u.setProvincia(usuari.getProvincia());
		u.setTelefon(usuari.getTelefon());
		u.setEmail(usuari.getEmail());
		u.setEmail2(usuari.getEmail2());
		u.setPassword(usuari.getPassword());
		u.setAcord(usuari.getAcord());
		
		try {      
				
				
				em.persist(u);
				      
				tx.commit();    
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
		
	}


	public void eliminarUsuari(Key keyUsuari) {
		
		
		EntityManager em = persistenceService.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
				Usuaris usuari = em.find(Usuaris.class, keyUsuari);
				em.remove(usuari);
     
				tx.commit();    
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
		
	}


	public Usuaris cercarUsuari(Key keyUsuari) {
		
		Usuaris usuari = null;
		
		EntityManager em = persistenceService.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {      
			
				usuari = em.find(Usuaris.class, keyUsuari);
     
				tx.commit();    
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
		
		return usuari;
	}
	
	public Usuaris cercarUsuari(String nomUsuari) {
		
		Usuaris usuari = null;
		
		EntityManager em = persistenceService.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {      
			
				Query q = em.createQuery("SELECT u FROM " + Usuaris.class.getName() + " u WHERE u.nom = :nomUsuari");
				q.setParameter("nomUsuari", nomUsuari);
				
				usuari = (Usuaris)q.getSingleResult();
				tx.commit();  
				
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
		
		return usuari;
	}	


	public Usuaris editPerfil(String keyUsuari) {
		
		Usuaris usuari = null;
		
		EntityManager em = persistenceService.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {   
				usuari = em.find( Usuaris.class, keyUsuari);
				 
				tx.commit();  
				
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
		
		return usuari;  
		
	}


	
	public Usuaris usuariValid(Usuaris usuari) {
		
		Boolean existeix = false;
		
		EntityManager em = persistenceService.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {    
			
			Query q = em.createQuery("SELECT u FROM " + Usuaris.class.getName() + " u WHERE u.nom = :nomUsuari");
			q.setParameter("nomUsuari", usuari.getNomusuari());
			
			usuari = (Usuaris)q.getSingleResult();

			    
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
		
		return usuari;
		
	}


	public boolean emailValid(String email) {
		
		Long num = null;
		Boolean existeix = false;
		    
		EntityManager em = persistenceService.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {      
			
			Query q = em.createQuery("select count(*) from Usuaris as usuari where usuari.email = '" + email + "'");
				
			num = (Long)q.getSingleResult();
			if (num == 0)
				existeix = false;
			else
				existeix = true;
				
			tx.commit();    
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
		
		return existeix;

	}

	

	public String passwordEmail(String email) {
		
		String password = null;
		    
		EntityManager em = persistenceService.getEntityManager(); 
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {   
			
			 Query q = em.createQuery(("select password from Usuaris as usuari where usuari.email = '" + email + "'"));
			
			 password = (String)q.getSingleResult();
			 
			 tx.commit();
			
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
		
		return password;

	}


	

	public String cambiaPassword(Usuaris usuari) {
		   
		EntityManager em = persistenceService.getEntityManager(); 
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {      
				
				em.persist(usuari);
				      
				tx.commit();    
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
		
		return null;
	}
	




	public String modificarUsuari(Usuaris usuari) {
		
		EntityManager em = persistenceService.getEntityManager();
		EntityTransaction tx = null;
		tx.begin();
		
		try {      
			
				em.persist(usuari);
				      
				tx.commit();    
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
		
		return null;
	}


	public String cambiaPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
