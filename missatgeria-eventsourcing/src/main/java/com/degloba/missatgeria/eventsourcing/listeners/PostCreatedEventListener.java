package com.degloba.missatgeria.eventsourcing.listeners;


import com.degloba.events.api.AbstractEventListener;
import com.degloba.missatgeria.eventsourcing.events.PostCreatedEvent;

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

