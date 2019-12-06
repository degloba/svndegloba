package com.degloba.eventsourcing.persona;

import lombok.Value;

@Value
public class PrivateAddressValidatedEvent {

    private final String addressId;

    private final String personId;

}
