package com.degloba.ecommerce.vendes.application.events.guava.eventbus.subscriber;


import com.degloba.ecommerce.vendes.application.events.CashPurchaseEvent;
import com.google.common.eventbus.EventBus;

public class SimpleEventBusExample {

	public static void main(String[] args) {
		  EventBus eventBus = new EventBus();
	        eventBus.register(new CashPurchaseEventSubscriber());
	        System.out.println("Post Simple EventBus Example");
	        eventBus.post(new CashPurchaseEvent(1223,"chocolate"));
	        
	        // Register MultiHandlerSubscriber "subscriber" in EventBus
	        MultiHandlerSubscriber.instance(eventBus);
	        eventBus.post(new CashPurchaseEvent(3333,"chocolate2"));

	}

}
