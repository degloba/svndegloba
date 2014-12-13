package com.degloba.boundedContext.vendes.domain;

import com.degloba.boundedContext.domain.IEntityService;

import domain.support.BaseAggregateRoot;
import domain.support.BaseEntity;


public interface IPagamentService {
	
	// a partir d'aqui tenim definits tots els repositoris del package : modules.modalpanel
	// un repositori per cada Entitat del domini
	IPagamentRepository<Long> rep = null;
	
	<E extends BaseAggregateRoot> IEntityService<E> createService(); 

}
