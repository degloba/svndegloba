package com.degloba.ecommerce.canonicalmodel.events;

import java.io.Serializable;

// Event
import com.degloba.event.annotations.Event;

//Google app engine
import com.google.appengine.api.datastore.Key;


@SuppressWarnings("serial")
@Event
public class PaymentRolledBackEvent implements Serializable{

	private Key paymentId;
	
	public PaymentRolledBackEvent(Key paymentId){
		this.paymentId = paymentId;
	}
	
	public Key getPaymentId() {
		return paymentId;
	}

}
