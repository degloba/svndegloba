package com.insacosa.interfaces;

import java.util.ArrayList;

import java.util.List;


import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.degloba.JPA.*;

import com.google.appengine.api.datastore.Key;

import com.degloba.JPA.PersistenceService;
import entitats.*;


public class Caracteristiques_Impl extends UtilCriteriaBuilderJPA<Caracteristiques> implements Caracteristiques_If {

	static PersistenceService persistenceService;
	
	public Caracteristiques_Impl() {
		super();
		// TODO Auto-generated constructor stub
		
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		
		//La classe PersistenceService es "ApplicationScoped"
		persistenceService = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{persistenceService}", PersistenceService.class);
	
	}
	
	public Caracteristiques_Impl(EntityManager entityManager, Class<Caracteristiques> entityClass) {
		super(entityManager, entityClass);		
	}
	

	
	public Caracteristiques caracteristicaCaractInmoble(Caractinmobles caractinmoble) {
		
	Caracteristiques c = null;
	
	EntityManager em = this.getEntityManager();
	  
	try {      		
			c = em.find(Caracteristiques.class, caractinmoble.getKey());
			 
	} catch (RuntimeException e) {
	    throw e; // or display error message
	}
	finally {
	    //em.close();
	}
	
	return c;
	}
	
	
	
	public List<Caracteristiques> caractTipus(Tipus tipus) {
		
		List<Caracteristiques> ret = new ArrayList<Caracteristiques>();
		
		EntityManager em = this.getEntityManager();
		  
		try {      
						
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Caracteristiques> q = cb.createQuery(Caracteristiques.class);
			Root<Caracteristiques> root = q.from(Caracteristiques.class);
			
			Path<Tipus> path = root.get("tipus");
			Predicate p = cb.equal(path, tipus);
			q.where(p);
			
			TypedQuery<Caracteristiques> tq = em.createQuery(q);
			ret = tq.getResultList();
			  
			 
		} catch (RuntimeException e) {
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}
		
		return ret;

	}

	
	public List<Caracteristiques> caractTipus(Tipus tipus, Integer control, Boolean inclouCaractComu) {
		
		List<Caracteristiques> ret = new ArrayList<Caracteristiques>();
		
		EntityManager em = this.getEntityManager();
				
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Caracteristiques> q = cb.createQuery(Caracteristiques.class);
		Root<Caracteristiques> root = q.from(Caracteristiques.class);
				
		try {
			
			if (inclouCaractComu)
			{
				//Tipus tipus99 = new Tipus();
				//tipus99.setId(KeyFactory.stringToKey("99"));
				
				//Path<Tipus> path = root.get("Tipus"); 
				Path<Integer> path2 = root.get("control"); 
	
	    		//Predicate p1 = cb.equal(path,  tipus); 
	    		//Predicate p2 = cb.equal(path,  tipus99); 
	    			
				//Predicate p3 = cb.or(p1,p2);
				Predicate p4 = cb.equal(path2,control);
	    		
				//cb.and(p3, p4);
				
				 				
			}
			else
			{
				
				//Path<Tipus> path = root.get("tipus"); 
				Path<Integer> path2 = root.get("control"); 
	
	    		//Predicate p1 = cb.equal(path,  tipus); 
	  			Predicate p2 = cb.equal(path2,control);
	    		
				//cb.and(p1, p2);
				
			}
						
			TypedQuery<Caracteristiques> query2 = em.createQuery(q);
			ret = query2.getResultList();
			
		}
		finally {
		    //em.close();
		}
	
		return ret;

	}

	
	/*
	 * Retorna el tipus de COLUMNA (VCHR,INT,DBL,DATE,,..) d'una caracteristica en concret
	 * Exemple : metres --> integer, adreça --> string, preu --> double , ...
	 */
	
	public String tipusColumnaCaract(Key keyCaract) {
		
		String ret = null;
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		  
		try {
			try {

				/*
				Criteria criteria = session.createCriteria(Caracteristiques.class)
				.add(Restrictions.eq("id",keyCaract))
				.setProjection(Projections.distinct(Projections.property("ttpbasic.ktpbasic"))); 
				
				Object r = criteria.uniqueResult();
				
				if (r !=null)
					ret = r.toString();
	*/
				tx.commit();    
				 
				} 			    
			catch (Exception e) {
				tx.rollback();
				}
			}
		finally {
			//em.close();
			}
		
		return ret;

	}


	
	public String tipusColumnaCaract(String nomCaract) {
			
		String ret = null;
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		  
		try {      
			try {
			
				/*
				Criteria criteria = session.createCriteria(Caracteristiques.class)
				.add(Restrictions.eq("nom",nomCaract))
				.setProjection(Projections.distinct(Projections.property("ttpbasic.ktpbasic"))); 
				
				Object r = criteria.uniqueResult();
				
				if (r !=null)
					ret = r.toString();
	*/
				tx.commit();    
				 
				} 			    
			catch (Exception e) {
				tx.rollback();
				}
			}
		finally {
			//em.close();
			}
		
		return ret;

	}

	

	/*
	 * Retorna el tipus de CONTROL UI (ITXT,SELT,IRAD,FILE,,..) d'una caracteristica en concret
	 * Exemple : metres --> ITXT, provincia --> SELT , ...
	 */
	
	public String tipusControlUICaract(Key keyCaract) {
		
		String ret = null;
		
		EntityManager em = this.getEntityManager();
		  
		try {
						
				/*
				Criteria criteria = session.createCriteria(Caracteristiques.class)
				.add(Restrictions.eq("id",keyCaract))
				.setProjection(Projections.distinct(Projections.property("ttpcontrol.ktpcontrol"))); 
				
				ret = criteria.uniqueResult().toString();
	*/
				
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<Caracteristiques> crit = cb.createQuery(Caracteristiques.class);
				
				Root<Caracteristiques> candidateRoot = crit.from(Caracteristiques.class);
				
				Path<Tipus> nameField = candidateRoot.get("id");
				Predicate nameEquals = cb.equal(nameField, keyCaract);
				crit.where(nameEquals);
					
				TypedQuery<Caracteristiques> q = em.createQuery(crit);
				
				TypedQuery<String> q2 = em.createQuery("SELECT c.ttpcontrol.tipus FROM Caracteristiques AS c", String.class);
				
				List<String> ret2 = q2.getResultList();
				ret = q.getSingleResult().getTtpcontrol().getTipus();

			
			}
		finally {
			//em.close();
			}
		
		return ret;

	}

	
	public String tipusControlUICaract(String nomCaract) {
		
		String ret = null;
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		  
		try {
			try {

				/*
				Criteria criteria = session.createCriteria(Caracteristiques.class)
				.add(Restrictions.eq("nom",nomCaract))
				.setProjection(Projections.distinct(Projections.property("ttpcontrol.ktpcontrol"))); 
				
				ret = criteria.uniqueResult().toString();
	*/
				tx.commit();    
				} 			    
			catch (Exception e) {
		    	tx.rollback();
				}
			}
		finally {
			//em.close();
			}
		
		return ret;

	}


	
	public Caracteristiques caractPerKey(String keyCaract) {
		
		Caracteristiques caracteristica = null;
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {     
				caracteristica = em.find( Caracteristiques.class, keyCaract);
				
				tx.commit();    
		} finally {        
			//em.close();    
		}
		return caracteristica; 

	}

	
	
	public List<Caracteristiques> allCaract() {
		
		List<Caracteristiques> ret = new ArrayList<Caracteristiques>();
		
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {      

			
			Query q = em.createQuery("SELECT c FROM " + Caracteristiques.class.getName());
			
			
			ret = (List<Caracteristiques>)q.getResultList();
			
			    
		} catch (RuntimeException e) {
		    if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}
		
		return ret;

	}


}
