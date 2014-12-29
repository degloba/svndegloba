package com.degloba.boundedContext.casino.domain;

import domain.support.BaseAggregateRoot;


public interface ICasinoClasseService {
	
	ICasinoClasseRepository rep = null;
	
	<E extends BaseAggregateRoot> IEntityService<E> createService(); 

}
