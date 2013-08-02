package com.insacosa.presentation.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import query.annotations.Finder;
import com.insacosa.presentation.ProvinciesFinder;
import com.insacosa.webui.ProvinciaItemDto;


/**
 * @author Pere Santasusana
 */
@Finder
public class JpaProvinciesFinder implements ProvinciesFinder {

    @PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<ProvinciaItemDto> findProvincies() {
		// TODO Auto-generated method stub
		return null;
	}


   
    
}
