package com.insacosa.presentation.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import query.annotations.Finder;

import com.insacosa.domain.Ciutats;
import com.insacosa.domain.Provincies;
import com.insacosa.presentation.CiutatsFinder;


/**
 * @author Pere Santasusana
 */
@Finder
public class JpaCiutatsFinder implements CiutatsFinder {

    @PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<Ciutats> findCiutats() {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Ciutats> ciutatsProvincia(Provincies provincia) {
		// TODO Auto-generated method stub
		return null;
	}

  
    
}
