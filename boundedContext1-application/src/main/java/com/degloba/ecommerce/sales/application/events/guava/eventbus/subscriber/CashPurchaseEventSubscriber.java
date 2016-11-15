package com.degloba.ecommerce.sales.application.events.guava.eventbus.subscriber;


import com.degloba.ecommerce.sales.application.events.guava.eventbus.events.CashPurchaseEvent;
import com.degloba.event.guava.eventbus.subscriber.EventSubscriber;
import com.google.common.eventbus.Subscribe;

public class CashPurchaseEventSubscriber extends EventSubscriber<CashPurchaseEvent> {


    public CashPurchaseEventSubscriber() {
    }

    @Subscribe
    public void handleEvent(CashPurchaseEvent event) {
        events.add(event);
    }

}