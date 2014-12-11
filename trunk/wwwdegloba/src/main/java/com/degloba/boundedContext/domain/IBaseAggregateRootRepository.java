package com.degloba.boundedContext.domain;

import domain.seedwork.IRepository;
import domain.support.BaseAggregateRoot;
import domain.support.BaseEntity;


public interface IBaseAggregateRootRepository extends IRepository<BaseAggregateRoot> {
	
	public IRepository<BaseAggregateRoot>  createRepository();
		
	// Funcions,Query
	
	// Modalpanel
	
	//public Modalpanel GetModalpanelByIdOnlyQuery(K id);
	
	
	
}
