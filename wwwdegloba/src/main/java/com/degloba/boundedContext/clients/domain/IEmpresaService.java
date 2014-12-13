package com.degloba.boundedContext.clients.domain;

import com.degloba.boundedContext.domain.IEntityService;

import domain.support.BaseAggregateRoot;
import domain.support.BaseEntity;


public interface IEmpresaService<K> {

	// a partir d'aqui tenim definits tots els repositoris del package : modules.client
	// un repositori per cada Entitat del domini
	 IEmpresaRepository<?> rep = null;
	
	<E extends BaseAggregateRoot> IEntityService<E> createService(); 

}
