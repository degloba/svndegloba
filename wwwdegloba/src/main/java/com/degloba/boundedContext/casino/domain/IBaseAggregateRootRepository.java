package com.degloba.boundedContext.casino.domain;

import domain.seedwork.IRepository;


public interface IBaseAggregateRootRepository extends IRepository {
	
	public IRepository createRepository();
		
	// Funcions,Query
	
	// Modalpanel
	
	//public Modalpanel GetModalpanelByIdOnlyQuery(K id);
	
	
	
}
