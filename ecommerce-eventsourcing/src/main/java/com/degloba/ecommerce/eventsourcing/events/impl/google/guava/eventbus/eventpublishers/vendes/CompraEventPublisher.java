package com.degloba.ecommerce.eventsourcing.events.impl.google.guava.eventbus.eventpublishers.vendes;


import com.degloba.ecommerce.compres.eventsourcing.events.CompraAmbCreditEvent;
import com.degloba.ecommerce.compres.eventsourcing.events.CompraEnEfectiuEvent;
import com.degloba.ecommerce.compres.eventsourcing.events.NoSubscriberEvent;
import com.google.common.eventbus.EventBus;

import lombok.AllArgsConstructor;

/**
 * @category Publicador d'events que utilitza {@link EventBus} de Google
 * 
 * @author degloba
 *
 */
@AllArgsConstructor
public class CompraEventPublisher {

	    EventBus eventBus;

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
