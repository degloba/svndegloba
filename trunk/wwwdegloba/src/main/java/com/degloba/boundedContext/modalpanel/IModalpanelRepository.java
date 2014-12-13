package com.degloba.boundedContext.domain.modules.modalpanel;

import domain.annotations.DomainRepository;


@DomainRepository
public interface IModalpanelRepository<K> {

	public Modalpanel load(K id);

	public Modalpanel save(Modalpanel modalpanel);
	
}
