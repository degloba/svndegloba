package com.degloba.boundedContext.infrastructure.repositories.impl;

// 
import com.degloba.boundedContext.domain.modalpanel.IModalpanelRepository;
import com.degloba.boundedContext.domain.modalpanel.Modalpanel;

// DDD
import domain.annotations.DomainRepositoryImpl;
import domain.canonicalmodel.publishedlanguage.AggregateId;

import infrastructure.repository.jpa.BaseAggregateRootJpaRepository;


@DomainRepositoryImpl
public class JpaModalpanelRepository extends BaseAggregateRootJpaRepository<Long,Modalpanel> implements IModalpanelRepository<Long>{


}

