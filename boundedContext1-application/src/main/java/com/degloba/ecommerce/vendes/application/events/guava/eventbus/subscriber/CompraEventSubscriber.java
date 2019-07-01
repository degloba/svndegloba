package com.degloba.ecommerce.vendes.application.events.guava.eventbus.subscriber;


import com.degloba.ecommerce.vendes.application.events.CompraEvent;
import com.degloba.event.bus.google.subscribers.EventSubscriber;
import com.google.common.eventbus.Subscribe;

/**
 * @category un {@link EventSubscriber} que tracta l'events de tipus {@link CompraEvent}<br>.
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
