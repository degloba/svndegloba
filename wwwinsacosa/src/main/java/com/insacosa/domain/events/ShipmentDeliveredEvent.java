package com.insacosa.domain.events;

import ddd.domain.DomainEvent;

public class ShipmentDeliveredEvent implements DomainEvent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Long shipmentId;

    public ShipmentDeliveredEvent(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Long getShipmentId() {
        return shipmentId;
    }
}
