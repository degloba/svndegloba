package com.degloba.organisation.domain.payment;

import javax.inject.Inject;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Transient;


//import com.degloba.annotations.AggregateRoot;
import com.degloba.domain.BaseAggregateRoot;
import com.degloba.organisation.canonicalmodel.events.PaymentRolledBackEvent;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.domain.canonicalmodel.publishedlanguage.ClientData;
import com.degloba.domain.sharedkernel.Money;
import com.google.appengine.api.datastore.Key;

/**
 * 
 * @author degloba
 *
 */
//@AggregateRoot
@Entity
public class Payment extends BaseAggregateRoot{

	@Embedded
	private ClientData clientData;
	
	@Embedded
	private Money amount;
	
	@Transient
	@Inject
	private PaymentFactory paymentFactory;
	
	@SuppressWarnings("unused")
	private Payment(){}
	
	Payment(Key aggregateId, ClientData clientData, Money amount) {
		//this.aggregateId = aggregateId;
		this.clientData = clientData;
		this.amount = amount;
	}

	public Payment rollBack(){
		//TODO explore domain rules
		eventPublisher.publish(new PaymentRolledBackEvent(getAggregateId()));
		return paymentFactory.createPayment(clientData, amount.multiplyBy(-1));
	}
}
