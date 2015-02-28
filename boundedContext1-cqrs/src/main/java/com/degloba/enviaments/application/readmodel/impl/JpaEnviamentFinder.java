package com.degloba.enviaments.application.readmodel.impl;

import java.util.List;



// JPA 
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;

// Entitat Domini


import com.degloba.annotations.FinderImpl;
import com.degloba.casino.enviaments.Enviament;
// DDD

import com.degloba.enviaments.application.readmodel.EnviamentDto;
import com.degloba.enviaments.application.readmodel.IEnviamentFinder;


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

	@Override
	public List<EnviamentDto> findShipment() {
		// TODO Auto-generated method stub
		return null;
	}

		



}