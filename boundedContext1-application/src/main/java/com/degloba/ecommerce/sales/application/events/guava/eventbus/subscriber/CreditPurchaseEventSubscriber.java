package com.degloba.ecommerce.sales.application.events.guava.eventbus.subscriber;


import com.degloba.ecommerce.sales.application.events.guava.eventbus.events.CreditPurchaseEvent;
import com.degloba.event.guava.eventbus.subscriber.EventSubscriber;
import com.google.common.eventbus.Subscribe;

public class CreditPurchaseEventSubscriber extends EventSubscriber<CreditPurchaseEvent> {

    private CreditPurchaseEventSubscriber() {
    }

    @Subscribe
    public void handleEvent(CreditPurchaseEvent event) {
        events.add(event);
    }
}