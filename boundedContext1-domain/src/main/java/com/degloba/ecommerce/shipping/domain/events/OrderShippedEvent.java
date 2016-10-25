package com.degloba.ecommerce.shipping.domain.events;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;


@SuppressWarnings("serial")
public class OrderShippedEvent implements Serializable {

    private final Key orderId;
    private final Key shipmentId;

    public OrderShippedEvent(Key orderId, Key shipmentId) {
        this.orderId = orderId;
        this.shipmentId = shipmentId;
    }

    public Key getOrderId() {
        return orderId;
    }

    public Key getShipmentId() {
        return shipmentId;
    }
}
