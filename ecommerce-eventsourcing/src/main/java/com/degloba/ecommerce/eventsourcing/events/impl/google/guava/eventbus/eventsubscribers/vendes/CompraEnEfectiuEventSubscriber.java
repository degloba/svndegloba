package com.degloba.ecommerce.eventsourcing.events.impl.google.guava.eventbus.eventsubscribers.vendes;



import com.degloba.ecommerce.compres.eventsourcing.events.CompraEnEfectiuEvent;
import com.degloba.events.bus.impl.google.subscribers.EventsSubscriber;
import com.google.common.eventbus.Subscribe;

import lombok.AllArgsConstructor;

/**
 * @category  és un {@link EventsSubscriber} que tracta events de tipus {@link CompraEnEfectiuEvent}<br>
 * Utilitza l'arquitectura de Google
 * 
 * @author degloba
 *
 */
@AllArgsConstructor
public class CompraEnEfectiuEventSubscriber extends EventsSubscriber<CompraEnEfectiuEvent> {


    @Subscribe
    public void handleEvent(CompraEnEfectiuEvent event) {
        events.add(event);
    }

}