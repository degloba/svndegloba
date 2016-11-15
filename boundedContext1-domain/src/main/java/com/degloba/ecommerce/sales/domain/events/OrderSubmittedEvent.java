package com.degloba.ecommerce.sales.domain.events;

import java.io.Serializable;

import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;
// Events
import com.degloba.event.annotations.Event;


@SuppressWarnings("serial")
@Event
public class OrderSubmittedEvent implements Serializable{

	private AggregateId orderId;
	
	public OrderSubmittedEvent(AggregateId orderId){
		this.orderId = orderId;
	}
	
	public AggregateId getOrderId() {
		return orderId;
	}

}
