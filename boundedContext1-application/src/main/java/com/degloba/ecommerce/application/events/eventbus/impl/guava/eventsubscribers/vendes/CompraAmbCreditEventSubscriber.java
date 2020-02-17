package com.degloba.ecommerce.application.events.eventbus.impl.guava.eventsubscribers.vendes;


import com.degloba.ecommerce.vendes.eventsourcing.events.CompraAmbCreditEvent;
import com.degloba.events.bus.subscribers.google.EventSubscriber;
import com.google.common.eventbus.Subscribe;

/**
 * @category un {@link EventSubscriber} que tracta els events de tipus {@link CompraAmbCreditEvent}.<br>
 * Utilitza l'arquitectura de Google
 * 
 * @author degloba
 *
 */
public class CompraAmbCreditEventSubscriber extends EventSubscriber<CompraAmbCreditEvent> {

    private CompraAmbCreditEventSubscriber() {
    }

    @Subscribe
    public void handleEvent(CompraAmbCreditEvent event) {
        events.add(event);
    }
}