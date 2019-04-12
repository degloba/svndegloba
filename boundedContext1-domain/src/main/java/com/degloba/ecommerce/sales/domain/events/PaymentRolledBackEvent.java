package com.degloba.ecommerce.sales.domain.events;

import java.io.Serializable;

import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
// Event
import com.degloba.event.annotations.Event;


@SuppressWarnings("serial")
@Event
public class PaymentRolledBackEvent implements Serializable{

	private AggregateId paymentId;
	
	public PaymentRolledBackEvent(AggregateId paymentId){
		this.paymentId = paymentId;
	}
	
	public AggregateId getPaymentId() {
		return paymentId;
	}

}
