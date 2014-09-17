package com.degloba.boundedContext.infrastructure.repositories.impl;

import com.degloba.boundedContext.domain.Client;
import com.degloba.boundedContext.domain.IClientRepository;

import infrastructure.repository.jpa.BaseAggregateRootJpaRepository;
import domain.annotations.DomainRepositoryImpl;
import domain.canonicalmodel.publishedlanguage.AggregateId;

@DomainRepositoryImpl
public class JpaClientRepository extends BaseAggregateRootJpaRepository<Client, AggregateId> implements IClientRepository<AggregateId> {

/*	@Override
	public Client load(AggregateId clientId) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public Client save(Client client) {
		// TODO Auto-generated method stub
		return null;
	}
//public class JpaClientRepository extends GenericJpaRepository<Client, Long> implements ClientRepository<Long> {

/*	@Override
	public Client load(AggregateId clientId) {
		// TODO Auto-generated method stub
		return null;
	}*/

}

