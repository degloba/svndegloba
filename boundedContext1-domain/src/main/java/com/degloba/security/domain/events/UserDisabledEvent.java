package com.degloba.security.domain.events;

import java.util.Date;

import com.degloba.event.annotations.Event;
import com.degloba.security.domain.persistence.rdbms.jpa.User;

/**
 */
@Event
public class UserDisabledEvent extends ActorDisabledEvent<User> {
    private User user;

    public UserDisabledEvent(User user) {
        super(user);
    }

    public UserDisabledEvent(User user, Date occurredOn) {
        super(user, occurredOn);
    }
}
