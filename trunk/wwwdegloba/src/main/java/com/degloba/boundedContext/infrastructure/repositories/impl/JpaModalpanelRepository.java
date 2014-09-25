package com.degloba.boundedContext.infrastructure.repositories.impl;

// DDD
import infrastructure.repository.jpa.BaseAggregateRootJpaRepository;
import domain.annotations.DomainRepositoryImpl;



import domain.canonicalmodel.publishedlanguage.AggregateId;

import com.degloba.boundedContext.domain.Modalpanel;
import com.degloba.boundedContext.domain.IModalpanelRepository;


@DomainRepositoryImpl
public class JpaModalpanelRepository extends BaseAggregateRootJpaRepository<AggregateId,Modalpanel> implements IModalpanelRepository<AggregateId>{


	@Override
	public Modalpanel save(Modalpanel modalpanel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Modalpanel load(AggregateId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Modalpanel load(Object object) {
		// TODO Auto-generated method stub
		return null;
	}


}

