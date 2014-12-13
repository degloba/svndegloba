package com.degloba.boundedContext.enviaments.infrastructure;

// 


// DDD
import com.degloba.boundedContext.enviaments.domain.Enviament;
import com.degloba.boundedContext.enviaments.domain.IEnviamentRepository;

import domain.annotations.DomainRepositoryImpl;
import domain.canonicalmodel.publishedlanguage.AggregateId;
import infrastructure.repository.jpa.BaseAggregateRootJpaRepository;


@DomainRepositoryImpl
public class JpaEnviamentRepository extends BaseAggregateRootJpaRepository<AggregateId,Enviament> implements IEnviamentRepository<AggregateId> {

	@Override
	public Enviament load(AggregateId aggregateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enviament save(Enviament modalpanel) {
		// TODO Auto-generated method stub
		return null;
	}

  
}

