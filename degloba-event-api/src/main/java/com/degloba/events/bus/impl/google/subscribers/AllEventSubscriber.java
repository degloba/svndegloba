package com.degloba.events.bus.subscribers.google;

import com.google.common.eventbus.Subscribe;

/**
 * @category  
 * 
 * @author degloba
 */
public class AllEventSubscriber extends EventSubscriber<Object> {

    private AllEventSubscriber() {
    }

    /**
     * @category 
     * 
     * @param event
     */
    @Subscribe
    public void handleEvent(Object event) {
        events.add(event);
    }
}