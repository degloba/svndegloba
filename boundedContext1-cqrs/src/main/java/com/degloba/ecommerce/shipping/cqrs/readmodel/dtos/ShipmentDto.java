package com.degloba.ecommerce.shipping.cqrs.readmodel.dtos;

import java.io.Serializable;


import com.google.appengine.api.datastore.Key;

import com.degloba.ecommerce.shipping.domain.ShippingStatus;

@SuppressWarnings("serial")
public class ShipmentDto implements Serializable {

    private Key shipmentId;
    private Key orderId;
    private ShippingStatus status;

    public ShipmentDto(Key shipmentId, Key orderId, ShippingStatus status) {
        this.shipmentId = shipmentId;
        this.orderId = orderId;
        this.status = status;
    }

    public Key getShipmentId() {
        return shipmentId;
    }

    public Key getOrderId() {
        return orderId;
    }

    public ShippingStatus getStatus() {
        return status;
    }
}
