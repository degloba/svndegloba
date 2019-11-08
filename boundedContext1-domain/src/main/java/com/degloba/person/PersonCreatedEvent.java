package com.degloba.eventsourcing.axon_multi.events;

import lombok.Value;

@Value
public class PersonCreatedEvent {

    private String personId;

    private String fullName;

}
