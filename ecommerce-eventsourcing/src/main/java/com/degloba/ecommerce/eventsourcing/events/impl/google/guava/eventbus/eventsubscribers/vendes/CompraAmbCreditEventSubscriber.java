package com.degloba.ecommerce.eventsourcing.events.impl.google.guava.eventbus.eventsubscribers.vendes;


import com.degloba.ecommerce.compres.eventsourcing.events.CompraAmbCreditEvent;
import com.degloba.events.bus.impl.google.subscribers.EventsSubscriber;
import com.google.common.eventbus.Subscribe;

/**
 * @category un {@link EventsSubscriber} que tracta els events de tipus {@link CompraAmbCreditEvent}.<br>
 * Utilitza l'arquitectura de Google
 * 
 * @author degloba
 *
 */
public class CompraAmbCreditEventSubscriber extends EventsSubscriber<CompraAmbCreditEvent> {

    private CompraAmbCreditEventSubscriber() {
    }

    @Subscribe
    public void handleEvent(CompraAmbCreditEvent event) {
        events.add(event);
    }
}