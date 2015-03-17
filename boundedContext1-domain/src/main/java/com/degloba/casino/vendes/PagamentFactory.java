package com.degloba.casino.vendes;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;



import com.degloba.annotations.DomainFactory;
import com.degloba.domain.canonicalmodel.publishedlanguage.ClientData;
import com.degloba.domain.sharedkernel.Money;


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
