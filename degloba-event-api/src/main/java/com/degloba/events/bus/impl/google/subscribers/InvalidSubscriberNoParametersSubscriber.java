package com.degloba.events.bus.impl.google.subscribers;


import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * 
 * @author degloba
 * 
 * @category implementat amb Google
 *
 */
public class InvalidSubscriberNoParametersSubscriber {

    private InvalidSubscriberNoParametersSubscriber() {
    }

    @Subscribe
    public void handleEvent() {
        //DO nothing will not work
    }

    public static InvalidSubscriberNoParametersSubscriber instance(EventBus eventBus) {
        InvalidSubscriberNoParametersSubscriber subscriber = new InvalidSubscriberNoParametersSubscriber();
        eventBus.register(subscriber);
        return subscriber;
    }
}