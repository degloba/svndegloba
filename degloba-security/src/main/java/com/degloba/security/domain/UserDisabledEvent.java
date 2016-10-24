package com.degloba.security.domain;

import java.util.Date;

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
