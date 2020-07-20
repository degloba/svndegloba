package com.degloba.ecommerce.eventsourcing.events.impl.google.guava.eventbus.eventsubscribers.vendes;

import com.degloba.ecommerce.compres.eventsourcing.events.CompraAmbCreditEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * @category Eventbus
 * 
 * @author pere
 *
 */
public class InvalidSubscriberMultipleParameterSubscriber {


    private InvalidSubscriberMultipleParameterSubscriber() {
    }

    @Subscribe
    public void handleCreditEvent(CompraAmbCreditEvent event) {
        //DO nothing this will not work
    }

    public static InvalidSubscriberMultipleParameterSubscriber instance(EventBus eventBus) {
        InvalidSubscriberMultipleParameterSubscriber subscriber = new InvalidSubscriberMultipleParameterSubscriber();
        eventBus.register(subscriber);
        return subscriber;
    }
}