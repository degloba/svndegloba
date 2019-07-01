package com.degloba.ecommerce.vendes.application.events.guava.eventbus.subscriber;



import com.degloba.ecommerce.vendes.application.events.CompraEnEfectiuEvent;
import com.degloba.event.bus.google.subscribers.EventSubscriber;
import com.google.common.eventbus.Subscribe;

/**
 * @category 
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