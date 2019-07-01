package com.degloba.event.bus.google.subscribers;

import com.google.common.eventbus.Subscribe;

/**
 * @category  
 */
public class AllEventSubscriber extends EventSubscriber<Object> {

    private AllEventSubscriber() {
    }

    @Subscribe
    public void handleEvent(Object event) {
        events.add(event);
    }
}