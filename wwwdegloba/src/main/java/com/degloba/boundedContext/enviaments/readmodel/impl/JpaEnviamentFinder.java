package com.degloba.boundedContext.enviaments.readmodel.impl;

import java.util.List;


// JPA 
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;


// Entitat Domini
import com.degloba.boundedContext.enviaments.domain.Enviament;
import com.degloba.boundedContext.enviaments.readmodel.IEnviamentFinder;


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