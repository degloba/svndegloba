package com.degloba.organitzacio.domain.events.security;

import java.util.Date;

import com.degloba.events.annotations.Event;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.security.Usuari;

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
