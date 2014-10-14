package com.degloba.boundedContext.infrastructure.repositories.impl;

// DDD
import com.degloba.boundedContext.domain.IModalpanelRepository;
import com.degloba.boundedContext.domain.Modalpanel;

import infrastructure.repository.jpa.BaseAggregateRootJpaRepository;
import domain.annotations.DomainRepositoryImpl;
import domain.canonicalmodel.publishedlanguage.AggregateId;



@DomainRepositoryImpl
public class JpaModalpanelRepository extends BaseAggregateRootJpaRepository<AggregateId,Modalpanel> implements IModalpanelRepository{


	@Override
	public Modalpanel save(Modalpanel modalpanel) {
		// TODO Auto-generated method stub
		
		this.persist(modalpanel);
		return null;
	}


}
