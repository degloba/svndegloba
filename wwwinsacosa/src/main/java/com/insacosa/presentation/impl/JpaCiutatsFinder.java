package com.insacosa.presentation.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import query.annotations.Finder;
import com.insacosa.presentation.CiutatsFinder;
import com.insacosa.webui.CiutatItemDto;
import com.insacosa.webui.ProvinciaItemDto;


/**
 * @author Pere Santasusana
 */
@Finder
public class JpaCiutatsFinder implements CiutatsFinder {

    @PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<CiutatItemDto> findCiutats() {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CiutatItemDto> ciutatsProvincia(ProvinciaItemDto provincia) {
		// TODO Auto-generated method stub
		return null;
	}


   
    
}
