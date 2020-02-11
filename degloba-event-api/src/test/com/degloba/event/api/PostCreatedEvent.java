package com.degloba.event.api;

import java.util.Date;

import com.degloba.events.api.AbstractEvent;


public class PostCreatedEvent extends AbstractEvent {
    public PostCreatedEvent() {
    }

    public PostCreatedEvent(Date occurredOn) {
        super(occurredOn);
    }

    public PostCreatedEvent(Date occurredOn, int version) {
        super(occurredOn, version);
    }
}
