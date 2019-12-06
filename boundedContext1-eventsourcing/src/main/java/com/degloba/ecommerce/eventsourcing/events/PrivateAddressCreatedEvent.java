package com.degloba.ecommerce.eventsourcing.events;

import lombok.Value;

@Value
public class PrivateAddressCreatedEvent {

    private final String addressId;

    private final String personId;

    private final String streetAndNumber;

    private final String zipCode;

}
