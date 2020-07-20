package com.degloba.ecommerce.eventsourcing.events.impl.google.guava.eventbus.eventsubscribers.vendes;


import com.degloba.ecommerce.compres.eventsourcing.events.CompraEvent;
import com.degloba.events.bus.impl.google.subscribers.EventsSubscriber;
import com.google.common.eventbus.Subscribe;

import lombok.AllArgsConstructor;

/**
 * @category un {@link EventsSubscriber} que tracta events de tipus {@link CompraEvent}<br>.
 * Utilitza l'arquitectura de Google
 * 
 * @author degloba
 *
 */
@AllArgsConstructor
public class CompraEventSubscriber extends EventsSubscriber<CompraEvent> {

    @Subscribe
    public void handleEvent(CompraEvent event) {
        events.add(event);
    }
    
}
