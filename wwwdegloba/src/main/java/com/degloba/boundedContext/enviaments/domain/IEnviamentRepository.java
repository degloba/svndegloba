package com.degloba.boundedContext.enviaments.domain;

import infrastructure.repository.jpa.IBaseAggregateRootJpaRepository;
import domain.annotations.DomainRepository;


@DomainRepository
public interface IEnviamentRepository<K> extends IBaseAggregateRootJpaRepository<Enviament>  {

	
}
