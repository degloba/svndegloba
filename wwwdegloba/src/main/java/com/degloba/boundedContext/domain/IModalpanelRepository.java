package com.degloba.boundedContext.domain;

import domain.annotations.DomainRepository;
import domain.canonicalmodel.publishedlanguage.AggregateId;

@DomainRepository
public interface IModalpanelRepository {

	public Modalpanel load(AggregateId aggregateId);

	public Modalpanel save(Modalpanel modalpanel);
	
}
