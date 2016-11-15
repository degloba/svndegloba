package com.degloba.ecommerce.sales.payment.domain.persistence.rdbms.jpa;

import javax.inject.Inject;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Transient;


import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.ClientData;
import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.domain.sharedkernel.Money;
import com.degloba.ecommerce.sales.domain.events.PaymentRolledBackEvent;
import com.degloba.ecommerce.sales.payment.domain.factories.PaymentFactory;


/**
 * 
 * @author degloba
 *
 */
//@AggregateRoot
@Entity
public class Payment extends BaseAggregateRoot{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Embedded
	private ClientData clientData;
	
	@Embedded
	private Money amount;
	
	@Transient
	@Inject
	private PaymentFactory paymentFactory;
	
	@SuppressWarnings("unused")
	private Payment(){}
	
	public Payment(long aggregateId, ClientData clientData, Money amount) {
		///////this.aggregateId = aggregateId;
		this.clientData = clientData;
		this.amount = amount;
	}

	public Payment rollBack(){
		//TODO explore domain rules
		eventPublisher.publish(new PaymentRolledBackEvent(getAggregateId()));
		return paymentFactory.createPayment(clientData, amount.multiplyBy(-1));
	}

}
