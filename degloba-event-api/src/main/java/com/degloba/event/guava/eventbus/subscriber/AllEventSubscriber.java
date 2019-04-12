package com.degloba.event.guava.eventbus.subscriber;

import com.google.common.eventbus.Subscribe;

/**
 * Classe : 
 */
public class AllEventSubscriber extends EventSubscriber<Object> {

    private AllEventSubscriber() {
    }

    @Subscribe
    public void handleEvent(Object event) {
        events.add(event);
    }
}