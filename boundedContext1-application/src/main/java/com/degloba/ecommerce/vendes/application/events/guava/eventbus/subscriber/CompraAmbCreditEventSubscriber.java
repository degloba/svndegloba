package com.degloba.ecommerce.vendes.application.events.guava.eventbus.subscriber;



import com.degloba.ecommerce.vendes.application.events.CompraAmbCreditEvent;
import com.degloba.event.guava.eventbus.subscriber.EventSubscriber;
import com.google.common.eventbus.Subscribe;

public class CreditPurchaseEventSubscriber extends EventSubscriber<CompraAmbCreditEvent> {

    private CreditPurchaseEventSubscriber() {
    }

    @Subscribe
    public void handleEvent(CompraAmbCreditEvent event) {
        events.add(event);
    }
}