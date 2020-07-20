package com.degloba.ecommerce.eventsourcing.events;

import lombok.Value;

@Value
public class PrivateAddressCreatedEvent {

    private final String adrecaId;

    private final String personaId;

    private final String carrerINumero;

    private final String zipCode;

}
