package com.degloba.ecommerce.canonicalmodel.events;

import java.io.Serializable;

// Events
import com.degloba.event.annotations.Event;

// Google app engine
import com.google.appengine.api.datastore.Key;


@SuppressWarnings("serial")
@Event
public class OrderSubmittedEvent implements Serializable{

	private long orderId;
	
	public OrderSubmittedEvent(long orderId){
		this.orderId = orderId;
	}
	
	public long getOrderId() {
		return orderId;
	}

}
