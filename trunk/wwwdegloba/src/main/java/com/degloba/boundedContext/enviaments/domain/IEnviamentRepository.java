package com.degloba.boundedContext.enviaments.domain;

import domain.annotations.DomainRepository;


@DomainRepository
public interface IEnviamentRepository<K> {

	public Enviament load(K aggregateId);

	public Enviament save(Enviament modalpanel);
	
}
