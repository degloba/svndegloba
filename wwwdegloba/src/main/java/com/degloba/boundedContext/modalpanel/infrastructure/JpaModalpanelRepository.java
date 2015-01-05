package com.degloba.boundedContext.modalpanel.infrastructure;


import com.degloba.boundedContext.modalpanel.domain.IModalpanelRepository;
import com.degloba.boundedContext.modalpanel.domain.Modalpanel;

import domain.annotations.DomainRepositoryImpl;
import domain.canonicalmodel.publishedlanguage.AggregateId;

import infrastructure.repository.jpa.BaseAggregateRootJpaRepository;


@DomainRepositoryImpl
public  class JpaModalpanelRepository extends BaseAggregateRootJpaRepository<Modalpanel>  implements IModalpanelRepository<AggregateId>{

	public JpaModalpanelRepository() {
		super();
		// TODO Auto-generated constructor stub
	}


	



	
	
}

