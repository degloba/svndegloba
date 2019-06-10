package com.degloba.ecommerce.enviaments.domain.events;

import java.io.Serializable;

import com.degloba.event.annotations.Event;

@SuppressWarnings("serial")
@Event
public class OrdreEnviadaEvent implements Serializable {

    private final long orderId;
    private final long shipmentId;

    public OrdreEnviadaEvent(long orderId, long shipmentId) {
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
