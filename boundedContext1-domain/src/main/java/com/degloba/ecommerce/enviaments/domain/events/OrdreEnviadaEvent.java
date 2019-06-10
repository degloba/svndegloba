package com.degloba.ecommerce.shipping.domain.events;

import java.io.Serializable;

import com.degloba.event.annotations.Event;

@SuppressWarnings("serial")
@Event
public class OrderShippedEvent implements Serializable {

    private final long orderId;
    private final long shipmentId;

    public OrderShippedEvent(long orderId, long shipmentId) {
        this.orderId = orderId;
        this.shipmentId = shipmentId;
    }

    public long getOrderId() {
        return orderId;
    }

    public long getShipmentId() {
        return shipmentId;
    }
}
