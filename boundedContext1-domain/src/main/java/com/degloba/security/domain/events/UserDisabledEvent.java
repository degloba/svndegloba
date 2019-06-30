package com.degloba.security.domain.events;

import java.util.Date;

import com.degloba.event.annotations.Event;
import com.degloba.security.domain.persistence.rdbms.jpa.Usuari;

/**
 */
@Event
public class UserDisabledEvent extends ActorDisabledEvent<Usuari> {
    private Usuari usuari;

    public UserDisabledEvent(Usuari usuari) {
        super(usuari);
    }

    public UserDisabledEvent(Usuari usuari, Date occurredOn) {
        super(usuari, occurredOn);
    }
}
