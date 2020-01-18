package com.degloba.ecommerce.eventuate;

import com.google.type.Money;
import com.networknt.eventuate.common.Event;

public class OrderCreatedEvent implements Event{

	public OrderCreatedEvent(Object customerId, Object orderTotal) {
		// TODO Auto-generated constructor stub
	}

	public String getCustomerId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Money getOrderTotal() {
		// TODO Auto-generated method stub
		return null;
	}

}
