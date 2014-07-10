package com.degloba.readmodel.impl;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;

// JPA - Persistencia
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


// CQRS
import query.annotations.Finder;


// Entitat Domini
import com.degloba.boundedContext.domain.Modalpanel;

// 
import com.degloba.readmodel.ModalpanelsFinder;
import com.degloba.webui.JSF.modalPanels;


// GAE


/**
 * @author Pere Santasusana
 */
@Finder
public class JpaModalpanelsFinder implements ModalpanelsFinder {

    @PersistenceContext
    private EntityManager entityManager;
    
    private static final Logger log = Logger.getLogger(modalPanels.class.getName());
    
    
	@SuppressWarnings("unchecked")
	public List<Modalpanel> findAll() {
		
		List<Modalpanel> ret = new ArrayList<Modalpanel>();

		//EntityTransaction tx = entityManager.getTransaction();
		//tx.begin();
		
		try {      
			
			Query q = entityManager.createQuery("select c FROM Modalpanel c");
			
			ret = (List<Modalpanel>)q.getResultList();
			ret.size();
			    
		} catch (RuntimeException e) {
			log.warning("Modalpanel error" + e.getMessage() );
		    //if ( tx != null && tx.isActive() ) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    //em.close();
		}

		return ret;

	}


    
}
