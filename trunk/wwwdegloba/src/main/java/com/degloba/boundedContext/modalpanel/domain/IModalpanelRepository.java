package com.degloba.boundedContext.modalpanel.domain;

import infrastructure.repository.jpa.IBaseAggregateRootJpaRepository;

import domain.annotations.DomainRepository;


@DomainRepository
public interface IModalpanelRepository<K> extends IBaseAggregateRootJpaRepository {

	
}
