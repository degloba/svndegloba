package com.degloba.ecommerce.sales.application.events.guava.eventbus.subscriber;


import com.degloba.ecommerce.sales.application.events.guava.eventbus.events.CashPurchaseEvent;
import com.google.common.eventbus.EventBus;

public class SimpleEventBusExample {

	public static void main(String[] args) {
		  EventBus eventBus = new EventBus();
	        eventBus.register(new CashPurchaseEventSubscriber());
	        System.out.println("Post Simple EventBus Example");
	        eventBus.post(new CashPurchaseEvent(1223,"chocolate"));

	}

}
