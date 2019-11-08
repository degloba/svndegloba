package com.degloba.person;

import lombok.Value;

@Value
public class PersonCreatedEvent {

    private String personId;

    private String fullName;

}
