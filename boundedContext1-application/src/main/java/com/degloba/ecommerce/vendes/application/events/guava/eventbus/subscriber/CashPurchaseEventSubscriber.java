package com.degloba.ecommerce.vendes.application.events.guava.eventbus.subscriber;



import com.degloba.ecommerce.vendes.application.events.CompraEnEfectiuEvent;
import com.degloba.event.guava.eventbus.subscriber.EventSubscriber;
import com.google.common.eventbus.Subscribe;

public class CashPurchaseEventSubscriber extends EventSubscriber<CompraEnEfectiuEvent> {


    public CashPurchaseEventSubscriber() {
    }

    @Subscribe
    public void handleEvent(CompraEnEfectiuEvent event) {
        events.add(event);
    }

}