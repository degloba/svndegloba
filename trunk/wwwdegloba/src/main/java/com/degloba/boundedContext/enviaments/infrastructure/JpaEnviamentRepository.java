package com.degloba.boundedContext.enviaments.infrastructure;


import com.degloba.boundedContext.enviaments.domain.Enviament;
import com.degloba.boundedContext.enviaments.domain.IEnviamentRepository;

import domain.annotations.DomainRepositoryImpl;
import domain.canonicalmodel.publishedlanguage.AggregateId;
import infrastructure.repository.jpa.BaseAggregateRootJpaRepository;


@DomainRepositoryImpl
public class JpaEnviamentRepository extends BaseAggregateRootJpaRepository<Enviament> implements IEnviamentRepository<AggregateId> {

	public JpaEnviamentRepository() {
		super();
		// TODO Auto-generated constructor stub
	}  

	

  
}

