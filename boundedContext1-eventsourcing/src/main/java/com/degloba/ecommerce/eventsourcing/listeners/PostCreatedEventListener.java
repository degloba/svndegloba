package com.degloba.ecommerce.eventsourcing.listeners;


import com.degloba.ecommerce.eventsourcing.events.PostCreatedEvent;
import com.degloba.events.api.AbstractEventListener;

public class PostCreatedEventListener extends AbstractEventListener<PostCreatedEvent> {

    private int count = 0;

    public int getCount() {
        return count;
    }

    @Override
    public void handle(PostCreatedEvent event) {
        count ++;
    }
}

