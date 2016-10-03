package com.degloba.ecommerce.shipping.domain.events;

import java.io.Serializable;


@SuppressWarnings("serial")
public class ShipmentDeliveredEvent implements Serializable {

    private final long shipmentId;

    public ShipmentDeliveredEvent(long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public long getShipmentId() {
        return shipmentId;
    }
}
