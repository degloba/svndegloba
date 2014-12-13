package com.degloba.boundedContext.casino.domain;

import domain.support.BaseAggregateRoot;
import domain.support.BaseEntity;


public interface ICasinoClasseService {
	
	ICasinoClasseRepository rep = null;
	
	<E extends BaseAggregateRoot> IEntityService<E> createService(); 

}
