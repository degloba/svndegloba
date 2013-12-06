package com.insacosa.presentation.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import query.annotations.Finder;

import com.insacosa.domain.Provincies;
import com.insacosa.presentation.ProvinciesFinder;


/**
 * @author Pere Santasusana
 */
@Finder
public class JpaProvinciesFinder implements ProvinciesFinder {

    @PersistenceContext
    private EntityManager entityManager;


	public List<Provincies> findProvincies() {
		// TODO Auto-generated method stub
		return null;
	}


	public Provincies provinciaPerKey(String newValue) {
		// TODO Auto-generated method stub
		return null;
	}


   
    
}
