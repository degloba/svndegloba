package com.degloba.boundedContext.domain;

import domain.annotations.DomainRepository;

@DomainRepository
public interface ModalpanelRepository<K> {

	public Modalpanel load(K modalpanelId);

	public Modalpanel save(Modalpanel modalpanel);
}
