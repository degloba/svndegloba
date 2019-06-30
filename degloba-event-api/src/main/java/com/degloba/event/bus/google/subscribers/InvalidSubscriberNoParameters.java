package com.degloba.event.guava.eventbus.subscriber;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

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