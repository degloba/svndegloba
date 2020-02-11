package com.degloba.ecommerce.application.events.eventbus.impl.guava.subscribers.vendes;



import com.degloba.ecommerce.vendes.eventsourcing.events.CompraEnEfectiuEvent;
import com.degloba.events.bus.subscribers.google.EventSubscriber;
import com.google.common.eventbus.Subscribe;

/**
 * @category  és un {@link EventSubscriber} que tracta events de tipus {@link CompraEnEfectiuEvent}<br>
 * Utilitza l'arquitectura de Google
 * 
 * @author degloba
 *
 */
public class CompraEnEfectiuEventSubscriber extends EventSubscriber<CompraEnEfectiuEvent> {


    public CompraEnEfectiuEventSubscriber() {
    }

    @Subscribe
    public void handleEvent(CompraEnEfectiuEvent event) {
        events.add(event);
    }

}