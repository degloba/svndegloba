package com.degloba.boundedContext.enviaments.readmodel.impl;

import java.util.List;


// JPA 
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;

import query.PaginatedResult;

// Entitat Domini


import com.degloba.boundedContext.enviaments.domain.Enviament;
import com.degloba.boundedContext.enviaments.readmodel.EnviamentDto;
import com.degloba.boundedContext.enviaments.readmodel.IEnviamentFinder;
import com.degloba.boundedContext.modalpanel.domain.Modalpanel;
import com.degloba.boundedContext.modalpanel.readmodel.ModalpanelDto;
import com.degloba.boundedContext.modalpanel.readmodel.ModalpanelQuery;
// 



// DDD
import domain.annotations.FinderImpl;


@FinderImpl
public class JpaEnviamentFinder implements IEnviamentFinder {

	@PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactory")
	private EntityManager entityManager;
	
	@Override
	public List<Enviament> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

		



}