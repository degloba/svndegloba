package com.degloba.ecommerce.vendes.application.events.guava.eventbus.subscriber;



import com.degloba.ecommerce.vendes.application.events.CompraAmbCreditEvent;
import com.degloba.ecommerce.vendes.application.events.CompraEvent;
import com.degloba.event.bus.google.subscribers.EventSubscriber;
import com.google.common.eventbus.Subscribe;

/**
 * @category un {@link EventSubscriber} que tracta els events de tipus {@link CompraAmbCreditEvent}<br>.
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