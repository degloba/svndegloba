package com.degloba.ecommerce.sales.payment.domain.factories;

import java.util.UUID;

import javax.inject.Inject;

import com.degloba.domain.annotations.DomainFactory;


// Domain
import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.ClientData;
import com.degloba.domain.sharedkernel.Money;
import com.degloba.ecommerce.sales.domain.events.ClientPaidEvent;
import com.degloba.ecommerce.sales.payment.domain.persistence.rdbms.jpa.Payment;
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
		//TODO validate
		
		///////Key aggregateId = KeyFactory.stringToKey( UUID.randomUUID().toString() );   //AggregateId.generate();
		publisher.publish(new ClientPaidEvent(1, clientData, amount));
		return new Payment(1, clientData, amount);
	}
}
