package com.degloba.ecommerce.vendes.application.events.guava.eventbus;


import com.degloba.ecommerce.vendes.cqrs.events.CompraAmbCreditEvent;
import com.degloba.ecommerce.vendes.cqrs.events.CompraEnEfectiuEvent;
import com.degloba.ecommerce.vendes.cqrs.events.NoSubscriberEvent;
import com.degloba.event.bus.google.events.SimpleEvent;
import com.google.common.eventbus.EventBus;

public class EventPublisher {

	    EventBus eventBus;

	    public EventPublisher(EventBus eventBus) {
	        this.eventBus = eventBus;
	    }

	    public void createCashPurchaseEvent(String description, long quantitat) {
	        eventBus.post(new CompraEnEfectiuEvent(quantitat, description));
	    }

	    public void createCreditPurchaseEvent(String item, String ccNumber, long quantitat) {
	        eventBus.post(new CompraAmbCreditEvent(quantitat, ccNumber, item));
	    }

	    public void createSimpleEvent(String eventName) {
	        eventBus.post(new SimpleEvent(eventName));
	    }

	    public void createNoSubscribedEvent() {
	        eventBus.post(new NoSubscriberEvent());
	    }

}
