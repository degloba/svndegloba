package com.degloba.ecommerce.eventsourcing.events.impl.google.guava.eventbus.eventsubscribers.vendes;

import com.degloba.ecommerce.compres.eventsourcing.events.CompraEnEfectiuEvent;
import com.google.common.eventbus.EventBus;

public class EventBusMain {

	public static void main(String[] args) {

		EventBus eventBus = new EventBus();
		eventBus.register(new CompraEnEfectiuEventSubscriber());
		System.out.println("Post Simple EventBus Example");
		eventBus.post(new CompraEnEfectiuEvent(1223, "chocolate"));

		// Register MultiHandlerSubscriber "subscriber" in EventBus
		CompraHandlerSubscriber.instance(eventBus);
		eventBus.post(new CompraEnEfectiuEvent(3333, "chocolate2"));

	}

}
