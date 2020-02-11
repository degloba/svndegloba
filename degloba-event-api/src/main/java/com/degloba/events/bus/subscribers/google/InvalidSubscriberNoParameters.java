package com.degloba.events.bus.subscribers.google;


import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * 
 * @author degloba
 * 
 * @category implementat amb Google
 *
 */
public class InvalidSubscriberNoParameters {

    private InvalidSubscriberNoParameters() {
    }

    @Subscribe
    public void handleEvent() {
        //DO nothing will not work
    }

    public static InvalidSubscriberNoParameters instance(EventBus eventBus) {
        InvalidSubscriberNoParameters subscriber = new InvalidSubscriberNoParameters();
        eventBus.register(subscriber);
        return subscriber;
    }
}