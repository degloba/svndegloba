package com.degloba.event.api;

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
