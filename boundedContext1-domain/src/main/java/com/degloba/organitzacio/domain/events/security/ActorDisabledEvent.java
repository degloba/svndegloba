package com.degloba.organitzacio.domain.events.security;


import java.util.Date;

import com.degloba.events.annotations.Event;
import com.degloba.events.api.AbstractEvent;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.security.Actor;

/**
 */
@Event
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
