package com.degloba.boundedContext.modalpanel;

import com.degloba.boundedContext.casino.domain.IEntityService;
import com.degloba.boundedContext.modalpanel.domain.IModalpanelRepository;

import domain.support.BaseAggregateRoot;

public interface IModalpanelService {
	
	// a partir d'aqui tenim definits tots els repositoris del package : modules.modalpanel
	// un repositori per cada Entitat del domini
	IModalpanelRepository<Long> rep = null;
	
	<E extends BaseAggregateRoot> IEntityService<E> createService(); 

}
