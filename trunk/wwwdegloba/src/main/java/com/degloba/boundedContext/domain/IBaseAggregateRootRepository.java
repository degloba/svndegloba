package com.degloba.boundedContext.domain;

import com.degloba.boundedContext.domain.modalpanel.Modalpanel;

import domain.seedwork.IRepository;
import domain.support.BaseAggregateRoot;
import domain.support.BaseEntity;


public interface IBaseAggregateRootRepository extends IRepository<BaseAggregateRoot> {
	
	public IRepository<BaseEntity>  createRepository();
		
	// Funcions,Query
	
	// Modalpanel
	
	//public Modalpanel GetModalpanelByIdOnlyQuery(K id);
	
	
	
}
