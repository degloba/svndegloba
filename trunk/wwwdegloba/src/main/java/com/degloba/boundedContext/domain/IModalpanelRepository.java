package com.degloba.boundedContext.domain;

import domain.annotations.DomainRepository;

@DomainRepository
public interface IModalpanelRepository<K> {

	public Modalpanel load(K modalpanelId);

	public Modalpanel save(Modalpanel modalpanel);
}
