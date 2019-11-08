package com.degloba.person;

import lombok.Value;

@Value
public class PrivateAddressAssignedEvent {

    private final String personId;

    private final String addressId;

}