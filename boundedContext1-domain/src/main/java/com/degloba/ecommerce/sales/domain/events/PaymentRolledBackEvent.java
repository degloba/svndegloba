package com.degloba.ecommerce.sales.domain.events;

import java.io.Serializable;

// Event
import com.degloba.event.annotations.Event;


@SuppressWarnings("serial")
@Event
public class PaymentRolledBackEvent implements Serializable{

	private long paymentId;
	
	public PaymentRolledBackEvent(long paymentId){
		this.paymentId = paymentId;
	}
	
	public long getPaymentId() {
		return paymentId;
	}

}
