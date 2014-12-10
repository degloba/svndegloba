package com.degloba.boundedContext.infrastructure.repositories.modules.modalpanel;

// 
import com.degloba.boundedContext.domain.modules.modalpanel.IModalpanelRepository;
import com.degloba.boundedContext.domain.modules.modalpanel.Modalpanel;

// DDD
import domain.annotations.DomainRepositoryImpl;
import domain.canonicalmodel.publishedlanguage.AggregateId;

import infrastructure.repository.jpa.BaseAggregateRootJpaRepository;


@DomainRepositoryImpl
public class JpaModalpanelRepository extends BaseAggregateRootJpaRepository<Long,Modalpanel> implements IModalpanelRepository<Long>{

	public JpaModalpanelRepository() {
		super();
		// TODO Auto-generated constructor stub
	}


}

