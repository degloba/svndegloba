package com.degloba.boundedContext.modalpanel.domain;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import domain.annotations.DomainFactory;


@DomainFactory
public class ModalpanelFactory {

	@Inject
	private AutowireCapableBeanFactory spring;
	
/*	@Inject
	private DomainEventPublisher domainEventPublisher;*/

	
/*	public Modalpanel create(ClientData clientData, Money amount){
		//TODO validate

		AggregateId aggregateId = AggregateId.generate();
		domainEventPublisher.publish(new ClientPaidEvent(aggregateId, clientData, amount));
		return new Modalpanel(aggregateId, clientData, amount);
	}*/

}
