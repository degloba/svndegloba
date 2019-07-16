package com.degloba.ecommerce.vendes.application.events.guava.eventbus.subscriber;



import com.degloba.ecommerce.vendes.eventsourcing.events.CompraEnEfectiuEvent;
import com.degloba.event.bus.google.subscribers.EventSubscriber;
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