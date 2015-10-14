package com.degloba.ecommerce.shipping.domain.events;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;

@SuppressWarnings("serial")
public class ShipmentDeliveredEvent implements Serializable {

    private final Key shipmentId;

    public ShipmentDeliveredEvent(Key shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Key getShipmentId() {
        return shipmentId;
    }
}
