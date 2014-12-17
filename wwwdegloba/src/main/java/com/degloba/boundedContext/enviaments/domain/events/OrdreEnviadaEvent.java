package com.degloba.boundedContext.enviaments.domain.events;

import java.io.Serializable;

import domain.canonicalmodel.publishedlanguage.AggregateId;


@SuppressWarnings("serial")
public class OrdreEnviadaEvent implements Serializable {

    private final AggregateId orderId;
    private final AggregateId shipmentId;

    public OrdreEnviadaEvent(AggregateId orderId, AggregateId shipmentId) {
        this.orderId = orderId;
        this.shipmentId = shipmentId;
    }

    public AggregateId getOrderId() {
        return orderId;
    }

    public AggregateId getShipmentId() {
        return shipmentId;
    }
}
