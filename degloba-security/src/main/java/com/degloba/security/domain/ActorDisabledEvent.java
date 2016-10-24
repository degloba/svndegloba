package com.degloba.security.domain;


import java.util.Date;

import com.degloba.event.api.AbstractEvent;

/**
 */
public class ActorDisabledEvent<T extends Actor> extends AbstractEvent {
    private T actor;

    public ActorDisabledEvent(T actor) {
        this.actor = actor;
    }

    public ActorDisabledEvent(T actor, Date occurredOn) {
        super(occurredOn);
        this.actor = actor;
    }

    public T getActor() {
        return actor;
    }
}
