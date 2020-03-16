package com.degloba.ecommerce.application.events.eventbus.impl.guava.eventsubscribers.vendes;


import com.degloba.ecommerce.vendes.eventsourcing.events.CompraEnEfectiuEvent;
import com.google.common.eventbus.EventBus;

public class SimpleEventBusExample {

	public static void main(String[] args) {
		  EventBus eventBus = new EventBus();
	        eventBus.register(new CompraEnEfectiuEventSubscriber());
	        System.out.println("Post Simple EventBus Example");
	        eventBus.post(new CompraEnEfectiuEvent(1223,"chocolate"));
	        
	        // Register MultiHandlerSubscriber "subscriber" in EventBus
	        MultiHandlerSubscriber.instance(eventBus);
	        eventBus.post(new CompraEnEfectiuEvent(3333,"chocolate2"));

	}

}