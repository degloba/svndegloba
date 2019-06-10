package com.degloba.ecommerce.vendes.domain.events;

import java.io.Serializable;

import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
// Events
import com.degloba.event.annotations.Event;


@SuppressWarnings("serial")
@Event
public class OrdreEnviadaEvent implements Serializable{

	private AggregateId orderId;
	
	public OrdreEnviadaEvent(AggregateId orderId){
		this.orderId = orderId;
	}
	
	public AggregateId getOrderId() {
		return orderId;
	}

}
