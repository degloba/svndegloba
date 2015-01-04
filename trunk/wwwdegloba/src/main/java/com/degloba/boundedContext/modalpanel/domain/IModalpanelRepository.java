package com.degloba.boundedContext.modalpanel.domain;

import java.io.Serializable;

import domain.annotations.DomainRepository;
import domain.canonicalmodel.publishedlanguage.AggregateId;


@DomainRepository
public interface IModalpanelRepository<K> {

	public Modalpanel load(AggregateId aggregateId);

	public Modalpanel save(Modalpanel modalpanel);
	
	public void persist(Modalpanel modalpanel);
	
	public Modalpanel load(Class<Modalpanel> clazz, Serializable id);
	
}
