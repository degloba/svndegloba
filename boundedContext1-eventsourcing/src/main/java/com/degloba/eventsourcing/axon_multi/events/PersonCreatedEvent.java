package com.degloba.eventsourcing.axon_multi.events;

import lombok.Value;

@Value
public class PersonCreatedEvent {

    private final String personId;

    private final String fullName;

}
