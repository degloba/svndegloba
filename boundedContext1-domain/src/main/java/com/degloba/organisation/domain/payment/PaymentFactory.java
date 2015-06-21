package com.degloba.organisation.domain.payment;

import java.util.UUID;

import javax.inject.Inject;

import com.degloba.annotations.DomainFactory;
import com.degloba.domain.DomainEventPublisher;
import com.degloba.organisation.canonicalmodel.events.ClientPaidEvent;
import com.degloba.utils.Utils;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.domain.canonicalmodel.publishedlanguage.ClientData;
import com.degloba.domain.sharedkernel.Money;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * 
 * @author Slawek
 *
 */
@DomainFactory
public class PaymentFactory {
	
	@Inject
	private DomainEventPublisher publisher;

	public Payment createPayment(ClientData clientData, Money amount){
		//TODO validate
		
		Key aggregateId = KeyFactory.stringToKey( UUID.randomUUID().toString() );   //AggregateId.generate();
		publisher.publish(new ClientPaidEvent(aggregateId, clientData, amount));
		return new Payment(aggregateId, clientData, amount);
	}
}