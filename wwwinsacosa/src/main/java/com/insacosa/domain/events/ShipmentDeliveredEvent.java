package com.insacosa.domain.events;

import ddd.domain.DomainEvent;

public class ShipmentDeliveredEvent implements DomainEvent {

    private final Long shipmentId;

    public ShipmentDeliveredEvent(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Long getShipmentId() {
        return shipmentId;
    }
}
