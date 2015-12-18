package com.degloba.security.domain;


import java.util.Date;

import com.degloba.event.api.AbstractEvent;

/**
 * Created by yyang on 15/2/10.
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
