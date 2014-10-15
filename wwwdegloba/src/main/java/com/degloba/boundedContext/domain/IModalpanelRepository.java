package com.degloba.boundedContext.domain;

import domain.annotations.DomainRepository;
import domain.canonicalmodel.publishedlanguage.AggregateId;

@DomainRepository
public interface IModalpanelRepository<K> {

	public Modalpanel load(K aggregateId);

	public Modalpanel save(Modalpanel modalpanel);
	
}
