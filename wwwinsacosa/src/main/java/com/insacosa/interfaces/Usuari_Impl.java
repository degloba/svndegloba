package com.insacosa.interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;


import com.insacosa.dataModels_JPA.PersistenceService;
import com.insacosa.entitats.*;

import com.degloba.Util;
import com.degloba.UtilCriteriaBuilderJPA;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Entity;

import com.google.appengine.api.datastore.Key;

import com.google.appengine.api.datastore.Transaction;
import com.google.common.collect.Lists;



public class Usuari_Impl extends UtilCriteriaBuilderJPA<Usuaris> implements Usuari_If {

	static PersistenceService persistenceService;
	
	public Usuari_Impl(EntityManager entityManager, Class<Usuaris> entityClass) {
		super(entityManager, entityClass);		
	}

	
	
	public void afegirUsuari(Usuaris usuari) {
			
		// Recuperem l'EntityManager
		
		// 		2 Opcions :
		// 			Opcio 1 --> Utilitzar classe PersistenceService
		EntityManager em = persistenceService.getEntityManager();
		// 			Opcio 2 --> Utilitzar classe EMF
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
		
		try {     
			
				usuari = em.find(Usuaris.class, keyUsuari);
				
		} catch (RuntimeException e) {
		   
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
		
		return usuari;
	}
	
	public Usuaris cercarUsuari(String nomUsuari) {
		
		Usuaris usuari = null;
		Entity usEntity = null;
		
		EntityManager em = persistenceService.getEntityManager();
		
		try {      
			
				javax.persistence.Query q1 = em.createQuery("SELECT u FROM " + Usuaris.class.getName() + " u WHERE u.nom = :nomUsuari");
				q1.setParameter("nomUsuari", nomUsuari);
				usuari = (Usuaris)q1.getSingleResult();
				
				
				// Get the Datastore Service
				DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
				Filter nomFilter =  new FilterPredicate("nom", FilterOperator.EQUAL, nomUsuari);
				Query q2 = new Query(Usuaris.class.getName()).setFilter(nomFilter);
				PreparedQuery pq = datastore.prepare(q2);
				usEntity = pq.asSingleEntity();
											
				
		} catch (RuntimeException e) {
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
		
		try {   
				usuari = em.find( Usuaris.class, keyUsuari);
				
		} catch (RuntimeException e) {

		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
		
		return usuari;  
		
	}

    
		
	public Usuaris usuariValid(Usuaris usuari) {
			
		EntityManager em = this.getEntityManager();
				
		
		try {    
			
			// Get the Datastore Service
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Filter nomFilter =  new Query.FilterPredicate("nomusuari", Query.FilterOperator.EQUAL, usuari.getNomusuari() );
			Query q2 = new Query("Usuaris").setFilter(nomFilter);
			PreparedQuery pq = datastore.prepare(q2);
			Entity usEntity = pq.asSingleEntity();
						
			Query query = new Query("Usuaris"); 
			query.addFilter("nom", FilterOperator.EQUAL, usuari.getNomusuari()); 
			usEntity = Util.getDatastoreServiceInstance().prepare(query).asSingleEntity(); 

			
		
			
			
			CriteriaQuery<Usuaris> crit = createSelectCriteriaQuery(new ArrayList<String>(){{add("nom");}}, new ArrayList<Object>(){{add("peresan");}}, null, null);
			//CriteriaQuery<Usuaris> crit = createSelectCriteriaQuery(new ArrayList<String>(), null, null, null);
				
			TypedQuery<Usuaris> query2 = em.createQuery(crit);
			List<Usuaris> results = query2.getResultList();
			
			System.out.println("0 - " + results.get(0).getNom() + " " + results.get(0).getCognoms());
			
			// **************************************************
			
			
			// FUNCIONA
			CriteriaBuilder cb = em.getCriteriaBuilder();  // comu
			
			crit = cb.createQuery(Usuaris.class);
			Root<Usuaris> candidateRoot = crit.from(Usuaris.class);  // FROM
			crit.select(candidateRoot);
			
			
			TypedQuery<Usuaris> query3 = em.createQuery(crit);
			Usuaris results3 = query3.getSingleResult();
			
			System.out.println("1 - " + results3.getNom() + " " + results3.getCognoms());
			
			// **************************************************			
			
			CriteriaQuery<Usuaris> crit2 = cb.createQuery(Usuaris.class);
			Root<Usuaris> c = crit2.from(Usuaris.class);			
						
			Selection<Usuaris> s = crit2.getSelection();
			Path<String> p = c.get("nom");
			
			
			Predicate predicate = cb.equal(p,  usuari.getNomusuari());  // WHERE
			crit2.where(predicate);

			query3 = em.createQuery(crit2);
			results3 = query3.getSingleResult();
			
			System.out.println("2 - " + results3.getNom() + " " + results3.getCognoms());
			
			// **************************************************	
			
			// FUNCIONA
			Path nameField = candidateRoot.get("nom");
			Predicate nameEquals = cb.equal(nameField, "peresan");
			crit.where(nameEquals);
			
			TypedQuery<Usuaris> query4 = em.createQuery(crit);
			Usuaris u= query4.getSingleResult();
			
			// **************************************************	
			
			// FUNCIONA
			javax.persistence.Query q = em.createQuery("SELECT u FROM " + Usuaris.class.getName() + " u WHERE u.nom = :nomUsuari");
			q.setParameter("nomUsuari", usuari.getNomusuari());
			
			return (Usuaris)q.getSingleResult();
			    
		} catch (NoResultException e) {
			return null;
		}
 	
	}


	public boolean emailValid(String email) {
		
		Long num = null;
		Boolean existeix = false;
		    
		EntityManager em = this.getEntityManager();
		
		try {      
			
			javax.persistence.Query q = em.createQuery("select count(*) from Usuaris as usuari where usuari.email = '" + email + "'");
				
			num = (Long)q.getSingleResult();
			if (num == 0)
				existeix = false;
			else
				existeix = true;
				 
		} catch (RuntimeException e) {

		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}   
		
		return existeix;

	}
	

	public String passwordEmail(String email) {
		
		String password = null;
		    
		EntityManager em = this.getEntityManager(); 
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {   
			
			 javax.persistence.Query q = em.createQuery(("select password from Usuaris as usuari where usuari.email = '" + email + "'"));
			
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
		   
		EntityManager em = this.getEntityManager(); 
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
		
		EntityManager em = this.getEntityManager();
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
