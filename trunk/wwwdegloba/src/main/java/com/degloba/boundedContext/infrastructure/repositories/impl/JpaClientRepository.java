package com.degloba.boundedContext.infrastructure.repositories.impl;

import com.degloba.boundedContext.domain.Client;
import com.degloba.boundedContext.domain.ClientRepository;

import infrastructure.repository.jpa.GenericJpaRepository;
import domain.annotations.DomainRepositoryImpl;
import domain.canonicalmodel.publishedlanguage.AggregateId;

@DomainRepositoryImpl
public class JpaClientRepository extends GenericJpaRepository<Client, AggregateId> implements ClientRepository<AggregateId> {
//public class JpaClientRepository extends GenericJpaRepository<Client, Long> implements ClientRepository<Long> {

/*	@Override
	public Client load(AggregateId clientId) {
		// TODO Auto-generated method stub
		return null;
	}*/

}

