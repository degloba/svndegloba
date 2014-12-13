package com.degloba.boundedContext.modalpanel.infrastructure;

// 


// DDD

import com.degloba.boundedContext.modalpanel.domain.IModalpanelRepository;
import com.degloba.boundedContext.modalpanel.domain.Modalpanel;

import domain.annotations.DomainRepositoryImpl;
import domain.canonicalmodel.publishedlanguage.AggregateId;
import infrastructure.repository.jpa.BaseAggregateRootJpaRepository;


@DomainRepositoryImpl
public class JpaModalpanelRepository extends BaseAggregateRootJpaRepository<AggregateId,Modalpanel> implements IModalpanelRepository<AggregateId>{

	public JpaModalpanelRepository() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Modalpanel load(AggregateId aggregateId) {
		// TODO Auto-generated method stub
		return null;
	}


}

