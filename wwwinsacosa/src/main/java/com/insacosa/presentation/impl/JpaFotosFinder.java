package com.insacosa.presentation.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import query.annotations.Finder;

import com.insacosa.domain.Fotos;
import com.insacosa.domain.Inmobles;
import com.insacosa.presentation.CiutatsFinder;
import com.insacosa.presentation.FotosFinder;
import com.insacosa.webui.CiutatItemDto;
import com.insacosa.webui.ProvinciaItemDto;


/**
 * @author Pere Santasusana
 */
@Finder
public class JpaFotosFinder implements FotosFinder {

    @PersistenceContext
    private EntityManager entityManager;

    
    @Override
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
