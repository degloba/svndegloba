package com.degloba.boundedContext.domain;

import com.degloba.boundedContext.domain.modalpanel.Modalpanel;

import domain.IRepository;
import domain.support.BaseAggregateRoot;


public interface IBaseAggregateRootRepository<K> extends IRepository<K, BaseAggregateRoot> {
	
	public IRepository<K, BaseAggregateRoot>  createRepository();
		
	// Funcions,Query
	
	// Modalpanel
	
	public Modalpanel GetModalpanelByIdOnlyQuery(K id);
	
	
	
}
