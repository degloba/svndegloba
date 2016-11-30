package com.degloba.ecommerce.sales.payment.domain.factories;

import javax.inject.Inject;

import com.degloba.domain.annotations.DomainFactory;
import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;
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
		publisher.publish(new ClientPaidEvent(AggregateId.generate(), clientData, amount));
		
		return new Payment(AggregateId.generate(), clientData, amount);
	}
}
