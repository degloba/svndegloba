package com.degloba.ecommerce.application.events.eventbus.impl.guava.eventpublishers.vendes;


import com.degloba.ecommerce.vendes.eventsourcing.events.CompraAmbCreditEvent;
import com.degloba.ecommerce.vendes.eventsourcing.events.CompraEnEfectiuEvent;
import com.degloba.ecommerce.vendes.eventsourcing.events.NoSubscriberEvent;
import com.google.common.eventbus.EventBus;

/**
 * @category publicador d'events que utilitza {@link EventBus} de Google
 * 
 * @author degloba
 *
 */
public class EventPublisher {

	    EventBus eventBus;

	    public EventPublisher(EventBus eventBus) {
	        this.eventBus = eventBus;
	    }

	    public void publicaCompraEnEfectiuEvent(String description, long quantitat) {
	        eventBus.post(new CompraEnEfectiuEvent(quantitat, description));
	    }

	    public void publicaCompraAmbCreditEvent(String item, String ccNumber, long quantitat) {
	        eventBus.post(new CompraAmbCreditEvent(quantitat, ccNumber, item));
	    }

	    public void createNoSubscribedEvent() {
	        eventBus.post(new NoSubscriberEvent());
	    }

}
