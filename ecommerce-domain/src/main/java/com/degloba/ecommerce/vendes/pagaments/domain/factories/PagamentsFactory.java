package com.degloba.ecommerce.vendes.pagaments.domain.factories;

import javax.inject.Inject;

import com.degloba.domain.annotations.DomainFactory;
import com.degloba.domain.events.DomainEvent;
import com.degloba.ecommerce.vendes.domain.events.PagatClientEvent;
import com.degloba.ecommerce.vendes.pagaments.domain.persistence.rdbms.jpa.Pagament;
import com.degloba.events.api.IDomainEvent;
import com.degloba.events.publishers.IDomainEventPublisher;
import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;
import com.degloba.persistence.rdbms.api.jpa.BaseEntity;
import com.degloba.persistence.rdbms.api.jpa.ClientData;


/**
 * @author degloba
 * 
 * @category Fàbrica de {@link Pagament}
 */
@DomainFactory
public class PagamentsFactory {
	
	@Inject
	private IDomainEventPublisher<IDomainEvent<?>> publisher;

	public Pagament creaPagament(ClientData clientData, Money quantitat){
		
		publisher.publica((IDomainEvent<?>) new PagatClientEvent(AggregateId.generate(), clientData, quantitat));
		
		return new Pagament(AggregateId.generate(), clientData, quantitat);
	}
}
