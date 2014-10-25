package com.degloba.boundedContext.infrastructure.repositories.impl;

// DDD
import com.degloba.boundedContext.domain.IModalpanelRepository;
import com.degloba.boundedContext.domain.IPersonRepository;
import com.degloba.boundedContext.domain.Modalpanel;
import com.degloba.boundedContext.domain.Person;

import infrastructure.repository.jpa.BaseAggregateRootJpaMongoDBRepository;
import infrastructure.repository.jpa.BaseAggregateRootJpaRepository;
import domain.annotations.DomainRepositoryImpl;
import domain.canonicalmodel.publishedlanguage.AggregateId;



@DomainRepositoryImpl
public class JpaPersonRepository extends BaseAggregateRootJpaMongoDBRepository<Long,Person> implements IPersonRepository<Long>{


	@Override
	public Person save(Person person) {
		// TODO Auto-generated method stub
		
		this.persist(person);
		return null;
	}

	@Override
	public Person load(Long aggregateId) {
		// TODO Auto-generated method stub
		return null;
	}


}

