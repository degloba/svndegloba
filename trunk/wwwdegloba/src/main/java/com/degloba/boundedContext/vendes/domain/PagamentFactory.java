package com.degloba.boundedContext.vendes.domain;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.degloba.sharedkernel.Money;

import domain.annotations.DomainFactory;

import domain.canonicalmodel.publishedlanguage.ClientData;


@DomainFactory
public class PagamentFactory {

	@Inject
	private AutowireCapableBeanFactory spring;
	
/*	@Inject
	private DomainEventPublisher domainEventPublisher;*/

	
	public Pagament createPagament(ClientData clientData, Money amount){
		//TODO validate
/*
		AggregateId aggregateId = AggregateId.generate();
		domainEventPublisher.publish(new ClientPaidEvent(aggregateId, clientData, amount));
		return new Modalpanel(aggregateId, clientData, amount);*/
		return null;
	}

}
