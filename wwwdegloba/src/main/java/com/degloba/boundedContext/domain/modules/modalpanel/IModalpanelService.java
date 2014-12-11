package com.degloba.boundedContext.domain.modules.modalpanel;

import com.degloba.boundedContext.domain.IEntityService;

import domain.support.BaseAggregateRoot;
import domain.support.BaseEntity;


public interface IModalpanelService {
	
	// a partir d'aqui tenim definits tots els repositoris del package : modules.modalpanel
	// un repositori per cada Entitat del domini
	IModalpanelRepository<Long> rep = null;
	
	<E extends BaseAggregateRoot> IEntityService<E> createService(); 

}
