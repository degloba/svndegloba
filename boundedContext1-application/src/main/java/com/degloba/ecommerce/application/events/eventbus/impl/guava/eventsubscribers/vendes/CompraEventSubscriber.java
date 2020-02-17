package com.degloba.ecommerce.application.events.eventbus.impl.guava.subscribers.vendes;


import com.degloba.ecommerce.vendes.eventsourcing.events.CompraEvent;
import com.degloba.events.bus.subscribers.google.EventSubscriber;
import com.google.common.eventbus.Subscribe;

/**
 * @category un {@link EventSubscriber} que tracta events de tipus {@link CompraEvent}<br>.
 * Utilitza l'arquitectura de Google
 * 
 * @author degloba
 *
 */
public class CompraEventSubscriber extends EventSubscriber<CompraEvent> {


    private CompraEventSubscriber() {
    }

    @Subscribe
    public void handleEvent(CompraEvent event) {
        events.add(event);
    }
    
    @Subscribe
    public void handleEvent(String event) {
        //events.add(event);
    }


}
