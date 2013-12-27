package com.degloba.presentacio.impl;

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

import com.degloba.domain.Modalpanel;

import com.degloba.presentacio.ModalpanelsFinder;
import com.google.appengine.api.datastore.Key;

/**
 * @author Pere Santasusana
 */
@Finder
public class JpaModalpanelsFinder implements ModalpanelsFinder {

    @PersistenceContext
    private EntityManager entityManager;
    
   
	public List<Modalpanel> allCaract() {
		
		List<Modalpanel> ret = new ArrayList<Modalpanel>();

		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		
		try {      

			
			Query q = entityManager.createQuery("SELECT c FROM " + Modalpanel.class.getName());
			
			
			ret = (List<Modalpanel>)q.getResultList();
			
			    
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
