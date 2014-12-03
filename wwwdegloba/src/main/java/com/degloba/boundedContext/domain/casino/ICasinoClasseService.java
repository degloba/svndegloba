package com.degloba.boundedContext.domain.casino;

import com.degloba.boundedContext.domain.IEntityService;

import domain.support.BaseEntity;


public interface ICasinoClasseService {
	
	ICasinoClasseRepository rep = null;
	
	<E extends BaseEntity> IEntityService<E> createService(); 

}
