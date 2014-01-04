package com.degloba.presentacio.impl;

import java.util.ArrayList;
import java.util.List;

// JPA - Persistencia
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


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
    
   
    public void intialize(Object entity, int depth) {
        //JpaUtils.initialize(entityManager, entity, depth);
    }
    
    
	@SuppressWarnings("unchecked")
	public List<Modalpanel> allCaract() {
		
		List<Modalpanel> ret = new ArrayList<Modalpanel>();

		//EntityTransaction tx = entityManager.getTransaction();
		//tx.begin();
		
		try {      
			
			Query q = entityManager.createQuery("select c FROM Modalpanel c");
			
			ret = (List<Modalpanel>)q.getResultList();
			ret.size();
			    
		} catch (RuntimeException e) {
		    //if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}

		return ret;

	}


    
}
