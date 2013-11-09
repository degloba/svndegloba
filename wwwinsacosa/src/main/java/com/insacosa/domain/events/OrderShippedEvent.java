package com.insacosa.domain.events;

import ddd.domain.DomainEvent;

public class OrderShippedEvent implements DomainEvent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Long orderId;
    private final Long shipmentId;

    public OrderShippedEvent(Long orderId, Long shipmentId) {
        this.orderId = orderId;
        this.shipmentId = shipmentId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getShipmentId() {
        return shipmentId;
    }
}
