package com.degloba.boundedContext.domain;

import domain.IRepository;
import domain.support.BaseAggregateRoot;
import domain.support.BaseEntity;

public interface IBaseAggregateRootRepository extends IRepository<Long, BaseAggregateRoot> {
	
	public IRepository<Long, BaseAggregateRoot>  createRepository();
		
	// Funcions,Query
	
	// Modalpanel
	
	public Modalpanel GetModalpanelByIdOnlyQuery(Long id);
	
	
	
}
