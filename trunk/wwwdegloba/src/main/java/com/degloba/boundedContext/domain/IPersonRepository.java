package com.degloba.boundedContext.domain;

import domain.annotations.DomainRepository;
import domain.canonicalmodel.publishedlanguage.AggregateId;

@DomainRepository
public interface IPersonRepository<K> {

	public Person load(K aggregateId);

	public Person save(Person modalpanel);
	
}
