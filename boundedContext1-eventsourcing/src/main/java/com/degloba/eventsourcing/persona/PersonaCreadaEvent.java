package com.degloba.eventsourcing.persona;

import lombok.Value;

@Value
public class PersonCreatedEvent {

    private final String personId;

    private final String fullName;

}
