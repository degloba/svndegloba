package com.degloba.eventsourcing.persona;

import lombok.Value;

@Value
public class PrivateAddressRejectedEvent {

    private final String addressId;

    private final String personId;
}
