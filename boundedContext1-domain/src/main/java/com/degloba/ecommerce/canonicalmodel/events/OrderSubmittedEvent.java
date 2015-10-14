package com.degloba.ecommerce.canonicalmodel.events;

import java.io.Serializable;

import com.degloba.annotations.event.Event;
import com.google.appengine.api.datastore.Key;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;

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
