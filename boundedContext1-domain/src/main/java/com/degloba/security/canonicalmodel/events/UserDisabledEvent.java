package com.degloba.security.canonicalmodel.events;

import java.util.Date;

import com.degloba.security.domain.persistence.rdbms.jpa.User;

/**
 */
public class UserDisabledEvent extends ActorDisabledEvent<User> {
    private User user;

    public UserDisabledEvent(User user) {
        super(user);
    }

    public UserDisabledEvent(User user, Date occurredOn) {
        super(user, occurredOn);
    }
}
