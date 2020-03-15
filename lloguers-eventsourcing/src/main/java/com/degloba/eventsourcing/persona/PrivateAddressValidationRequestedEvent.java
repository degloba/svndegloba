package com.degloba.eventsourcing.persona;

import lombok.Value;

@Value
public class PrivateAddressValidationRequestedEvent {

    private final String adrecaId;

    private final String personaId;

}