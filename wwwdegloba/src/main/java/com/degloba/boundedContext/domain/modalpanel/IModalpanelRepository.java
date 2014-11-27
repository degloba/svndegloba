package com.degloba.boundedContext.domain.modalpanel;

import domain.annotations.DomainRepository;


@DomainRepository
public interface IModalpanelRepository<K> {

	public Modalpanel load(K aggregateId);

	public Modalpanel save(Modalpanel modalpanel);
	
}
