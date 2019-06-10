package com.degloba.ecommerce.shipping.cqrs.readmodel.dtos;

import java.io.Serializable;

import com.degloba.ecommerce.shipping.domain.ShippingStatus;

@SuppressWarnings("serial")
public class ShipmentDto implements Serializable {

    private long shipmentId;
    private long orderId;
    private ShippingStatus status;

    public ShipmentDto(long shipmentId, long orderId, ShippingStatus status) {
        this.shipmentId = shipmentId;
        this.orderId = orderId;
        this.status = status;
    }

    public long getShipmentId() {
        return shipmentId;
    }

    public long getOrderId() {
        return orderId;
    }

    public ShippingStatus getStatus() {
        return status;
    }
}
