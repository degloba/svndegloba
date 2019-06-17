package com.degloba.ecommerce.vendes.application.events.guava.eventbus.subscriber;



import com.degloba.ecommerce.vendes.application.events.CreditPurchaseEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class InvalidSubscriberMultipleParameter {


    private InvalidSubscriberMultipleParameter() {
    }

    @Subscribe
    public void handleCreditEvent(CreditPurchaseEvent event) {
        //DO nothing this will not work
    }

    public static InvalidSubscriberMultipleParameter instance(EventBus eventBus) {
        InvalidSubscriberMultipleParameter subscriber = new InvalidSubscriberMultipleParameter();
        eventBus.register(subscriber);
        return subscriber;
    }
}