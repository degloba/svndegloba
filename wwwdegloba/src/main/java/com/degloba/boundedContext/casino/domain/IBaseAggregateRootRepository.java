package com.degloba.boundedContext.casino.domain;

import domain.seedwork.IRepository;
import domain.support.BaseAggregateRoot;


public interface IBaseAggregateRootRepository extends IRepository {
	
	public IRepository createRepository();
		
	// Funcions,Query
	
	// Modalpanel
	
	//public Modalpanel GetModalpanelByIdOnlyQuery(K id);
	
	
	
}
