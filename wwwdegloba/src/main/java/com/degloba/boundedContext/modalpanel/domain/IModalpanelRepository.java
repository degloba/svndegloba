package com.degloba.boundedContext.modalpanel.domain;

import domain.annotations.DomainRepository;
import domain.canonicalmodel.publishedlanguage.AggregateId;


@DomainRepository
public interface IModalpanelRepository<K> {

	public Modalpanel load(AggregateId aggregateId);

	public Modalpanel save(Modalpanel modalpanel);
	
	public void persist(Modalpanel modalpanel);
	
}
