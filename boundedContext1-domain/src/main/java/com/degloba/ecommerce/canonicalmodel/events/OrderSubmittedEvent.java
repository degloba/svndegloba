package com.degloba.ecommerce.canonicalmodel.events;

import java.io.Serializable;

// Events
import com.degloba.event.annotations.Event;

// Google app engine
import com.google.appengine.api.datastore.Key;


@SuppressWarnings("serial")
@Event
public class OrderSubmittedEvent implements Serializable{

	private Key orderId;
	
	public OrderSubmittedEvent(Key orderId){
		this.orderId = orderId;
	}
	
	public Key getOrderId() {
		return orderId;
	}

}
