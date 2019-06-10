package com.degloba.ecommerce.vendes.pagaments.domain.factories;

import javax.inject.Inject;

import com.degloba.domain.annotations.DomainFactory;
import com.degloba.ecommerce.vendes.domain.events.PagatClientEvent;
import com.degloba.ecommerce.vendes.pagaments.domain.persistence.rdbms.jpa.Payment;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
import com.degloba.persistence.domain.AggregateId;
// Domain
import com.degloba.persistence.domain.ClientData;
import com.degloba.persistence.domain.sharedkernel.Money;
// Event
import com.degloba.event.domain.IDomainEventPublisher;


/**
 * 
 * @author degloba
 *
 */
@DomainFactory
public class PaymentFactory {
	
	@Inject
	private IDomainEventPublisher<?> publisher;

	public Payment createPayment(ClientData clientData, Money amount){
		publisher.publish(new PagatClientEvent(AggregateId.generate(), clientData, amount));
		
		return new Payment(AggregateId.generate(), clientData, amount);
	}
}
