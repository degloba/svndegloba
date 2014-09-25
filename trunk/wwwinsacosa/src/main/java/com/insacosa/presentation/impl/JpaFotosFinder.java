package com.insacosa.presentation.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import query.annotations.Finder;

import com.insacosa.Inmobles.domain.Fotos;
import com.insacosa.Inmobles.domain.Inmobles;
import com.insacosa.presentation.FotosFinder;


/**
 * @author Pere Santasusana
 */
@Finder
public class JpaFotosFinder implements FotosFinder {

    @PersistenceContext
    private EntityManager entityManager;

    
    
	public List<Fotos> fotosInmoble(Inmobles inmoble) {
		
		List<Fotos> ret = new ArrayList<Fotos>();
		
		EntityTransaction tx = entityManager.getTransaction();
		  
		try {      
	
				/*
			Query query = session.createQuery("1");
				
			ret = session.createCriteria(Fotos.class)
				.add(Expression.eq("inmobles",inmoble))
				.list();

				*/		
			tx.commit();    
			 
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