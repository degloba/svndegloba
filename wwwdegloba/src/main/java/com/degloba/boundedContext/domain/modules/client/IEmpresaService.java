package com.degloba.boundedContext.domain.modules.client;

import com.degloba.boundedContext.domain.IEntityService;

import domain.support.BaseAggregateRoot;
import domain.support.BaseEntity;


public interface IEmpresaService {

	// a partir d'aqui tenim definits tots els repositoris del package : modules.client
	// un repositori per cada Entitat del domini
	IEmpresaRepository<Long> rep = null;
	
	<E extends BaseAggregateRoot> IEntityService<E> createService(); 

}
