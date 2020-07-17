package com.degloba.events.bus.impl.google.subscribers;

import com.google.common.eventbus.Subscribe;

/**
 * @category  
 * 
 * @author degloba
 */
public class AllEventSubscriber extends EventsSubscriber<Object> {

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