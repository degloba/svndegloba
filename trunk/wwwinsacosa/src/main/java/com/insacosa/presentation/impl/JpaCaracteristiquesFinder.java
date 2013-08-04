package com.insacosa.presentation.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import query.annotations.Finder;

import com.google.appengine.api.datastore.Key;
import com.insacosa.domain.Caracteristiques;
import com.insacosa.domain.Caractinmobles;
import com.insacosa.domain.Tipus;
import com.insacosa.presentation.CaracteristiquesFinder;
import com.insacosa.presentation.CiutatsFinder;
import com.insacosa.webui.CiutatItemDto;
import com.insacosa.webui.ProvinciaItemDto;


/**
 * @author Pere Santasusana
 */
@Finder
public class JpaCaracteristiquesFinder implements CaracteristiquesFinder {

    @PersistenceContext
    private EntityManager entityManager;
    
    
    @Override
    public List<Caracteristiques> caractTipus(Tipus tipus, Integer condicio, Boolean inclouCaractComu) {
		
		List<Caracteristiques> ret = new ArrayList<Caracteristiques>();
				
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
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
						
			TypedQuery<Caracteristiques> query2 = entityManager.createQuery(q);
			ret = query2.getResultList();
			
		}
		finally {
		    //em.close();
		}
	
		return ret;

		return null;
	}

    @Override
    public Caracteristiques caracteristicaCaractInmoble(Caractinmobles caractinmoble) {
		
		Caracteristiques c = null;
		
		try {      		
				c = entityManager.find(Caracteristiques.class, caractinmoble.getKey());
				 
		} catch (RuntimeException e) {
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}
		
		return c;
	}
   
    
    /*
	 * Retorna el tipus de COLUMNA (VCHR,INT,DBL,DATE,,..) d'una caracteristica en concret
	 * Exemple : metres --> integer, adreça --> string, preu --> double , ...
	 */
	
    @Override
	public String tipusColumnaCaract(Key keyCaract) {
		
		String ret = null;
		
		EntityTransaction tx = entityManager.getTransaction();
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


    @Override
	public String tipusColumnaCaract(String nomCaract) {
			
		String ret = null;
		
		EntityTransaction tx = entityManager.getTransaction();
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
    @Override
	public String tipusControlUICaract(Key keyCaract) {
		
		String ret = null;
		  
		try {
						
				/*
				Criteria criteria = session.createCriteria(Caracteristiques.class)
				.add(Restrictions.eq("id",keyCaract))
				.setProjection(Projections.distinct(Projections.property("ttpcontrol.ktpcontrol"))); 
				
				ret = criteria.uniqueResult().toString();
	*/
				
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<Caracteristiques> crit = cb.createQuery(Caracteristiques.class);
				
				Root<Caracteristiques> candidateRoot = crit.from(Caracteristiques.class);
				
				Path<Tipus> nameField = candidateRoot.get("id");
				Predicate nameEquals = cb.equal(nameField, keyCaract);
				crit.where(nameEquals);
					
				TypedQuery<Caracteristiques> q = entityManager.createQuery(crit);
				
				TypedQuery<String> q2 = entityManager.createQuery("SELECT c.ttpcontrol.tipus FROM Caracteristiques AS c", String.class);
				
				List<String> ret2 = q2.getResultList();
				ret = q.getSingleResult().getTtpcontrol().getTipus();

			
			}
		finally {
			//em.close();
			}
		
		return ret;

	}

    @Override
	public String tipusControlUICaract(String nomCaract) {
		
		String ret = null;
		
		EntityTransaction tx = entityManager.getTransaction();
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


    @Override
	public Caracteristiques caractPerKey(String keyCaract) {
		
		Caracteristiques caracteristica = null;

		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		
		try {     
				caracteristica = entityManager.find( Caracteristiques.class, keyCaract);
				
				tx.commit();    
		} finally {        
			//em.close();    
		}
		return caracteristica; 

	}

	
    @Override
	public List<Caracteristiques> allCaract() {
		
		List<Caracteristiques> ret = new ArrayList<Caracteristiques>();

		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		
		try {      

			
			Query q = entityManager.createQuery("SELECT c FROM " + Caracteristiques.class.getName());
			
			
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
