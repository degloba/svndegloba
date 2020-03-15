package com.degloba.eventsourcing.persona;

import lombok.Value;

@Value
public class PrivateAddressAssignedEvent {

    private final String personaId;

    private final String adrecaId;

}
