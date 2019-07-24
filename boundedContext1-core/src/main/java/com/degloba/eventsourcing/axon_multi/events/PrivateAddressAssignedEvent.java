package com.degloba.eventsourcing.axon_multi.events;

import lombok.Value;

@Value
public class PrivateAddressAssignedEvent {

    private final String personId;

    private final String addressId;

}