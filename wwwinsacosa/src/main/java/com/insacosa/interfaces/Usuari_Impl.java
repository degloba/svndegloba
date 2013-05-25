package com.insacosa.interfaces;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
		
		EntityManager em = this.getEntityManager();
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
		
		EntityManager em = this.getEntityManager();
		
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
		
		EntityManager em = this.getEntityManager();
		
		try {      
			
				javax.persistence.Query q1 = em.createQuery("SELECT u FROM " + Usuaris.class.getName() + " u WHERE u.nom = :nomUsuari");
				q1.setParameter("nomUsuari", nomUsuari);
				usuari = (Usuaris)q1.getSingleResult();
				
				// **************************************************
				
				CriteriaBuilder cb = em.getCriteriaBuilder();				
				CriteriaQuery<Usuaris> cq = cb.createQuery(Usuaris.class);
				Root<Usuaris> c = cq.from(Usuaris.class);			
							
				Selection<Usuaris> s = cq.getSelection();
				Path<String> p = c.get("nom");
							
				Predicate predicate = cb.equal(p,  usuari.getNomusuari());  // WHERE
				cq.where(predicate);

				TypedQuery<Usuaris> tq = em.createQuery(cq);
				usuari = tq.getSingleResult();					
				
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
		
		EntityManager em = this.getEntityManager();
		
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

			// **************************************************
			
			CriteriaQuery<Usuaris> crit = createSelectCriteriaQuery(new ArrayList<String>(){{add("nom");}}, new ArrayList<Object>(){{add("peresan");}}, null, null);
							
			TypedQuery<Usuaris> tq = em.createQuery(crit);
			List<Usuaris> results = tq.getResultList();
			
			System.out.println("0 - " + results.get(0).getNom() + " " + results.get(0).getCognoms());
			
			// **************************************************
			

			CriteriaBuilder cb = em.getCriteriaBuilder();  // comu
			
			crit = cb.createQuery(Usuaris.class);
			Root<Usuaris> root = crit.from(Usuaris.class);  // FROM
			crit.select(root);
						
			tq = em.createQuery(crit);
			Usuaris results3 = tq.getSingleResult();
			
			System.out.println("1 - " + results3.getNom() + " " + results3.getCognoms());
			
			// **************************************************			
			
			CriteriaQuery<Usuaris> cq = cb.createQuery(Usuaris.class);
			Root<Usuaris> r = cq.from(Usuaris.class);			
						
			Selection<Usuaris> s = cq.getSelection();
			Path<String> p = r.get("nom");
						
			Predicate predicate = cb.equal(p,  usuari.getNomusuari());  // WHERE
			cq.where(predicate);

			tq = em.createQuery(cq);
			results3 = tq.getSingleResult();
			
			System.out.println("2 - " + results3.getNom() + " " + results3.getCognoms());
			
			// **************************************************	
			
			// FUNCIONA
			p = root.get("nom");
			Predicate pr = cb.equal(p, "peresan");
			crit.where(pr);
			
			tq = em.createQuery(crit);
			Usuaris u= tq.getSingleResult();
			
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
				
		Boolean existeix = false;
		    
		EntityManager em = this.getEntityManager();
		
		try {      
			
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Boolean> cq = cb.createQuery(Boolean.class);
			Root<Usuaris> r = cq.from(Usuaris.class);			
						
				
			Path<String> p = r.get("email");
						
			Predicate predicate = cb.equal(p,  email);  // WHERE
			cq.where(predicate);
			
			cq.select(cb.count(cq.from(Usuaris.class)).as(Boolean.class));

			existeix = em.createQuery(cq).getSingleResult();
					
				 
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
