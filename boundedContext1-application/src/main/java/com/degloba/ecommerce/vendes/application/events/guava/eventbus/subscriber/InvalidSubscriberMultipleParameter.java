package com.degloba.ecommerce.vendes.application.events.guava.eventbus.subscriber;



import com.degloba.ecommerce.vendes.cqrs.events.CompraAmbCreditEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class InvalidSubscriberMultipleParameter {


    private InvalidSubscriberMultipleParameter() {
    }

    @Subscribe
    public void handleCreditEvent(CompraAmbCreditEvent event) {
        //DO nothing this will not work
    }

    public static InvalidSubscriberMultipleParameter instance(EventBus eventBus) {
        InvalidSubscriberMultipleParameter subscriber = new InvalidSubscriberMultipleParameter();
        eventBus.register(subscriber);
        return subscriber;
    }
}