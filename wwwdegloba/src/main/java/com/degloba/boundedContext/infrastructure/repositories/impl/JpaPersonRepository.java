package com.degloba.boundedContext.infrastructure.repositories.impl;


import com.degloba.boundedContext.domain.IPersonRepository;

// Entitats domini
import com.degloba.boundedContext.domain.Person;

//DDD
import infrastructure.repository.jpa.BaseAggregateRootJpaMongoDBRepository;
import domain.annotations.DomainRepositoryImpl;
import domain.canonicalmodel.publishedlanguage.AggregateId;


@DomainRepositoryImpl
public class JpaPersonRepository extends BaseAggregateRootJpaMongoDBRepository<Long,Person> implements IPersonRepository<Long>{


}

