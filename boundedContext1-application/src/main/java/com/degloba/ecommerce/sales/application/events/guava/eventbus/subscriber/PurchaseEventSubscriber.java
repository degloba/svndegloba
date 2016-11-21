package com.degloba.ecommerce.sales.application.events.guava.eventbus.subscriber;


import com.degloba.ecommerce.sales.application.events.PurchaseEvent;
import com.degloba.event.guava.eventbus.subscriber.EventSubscriber;
import com.google.common.eventbus.Subscribe;

public class PurchaseEventSubscriber extends EventSubscriber<PurchaseEvent> {


    private PurchaseEventSubscriber() {
    }

    @Subscribe
    public void handleEvent(PurchaseEvent event) {
        events.add(event);
    }
    
    @Subscribe
    public void handleEvent(String event) {
        //events.add(event);
    }


}
