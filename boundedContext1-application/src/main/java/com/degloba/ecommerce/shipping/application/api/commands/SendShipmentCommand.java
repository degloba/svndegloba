package com.degloba.ecommerce.shipping.application.api.commands;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import command.annotations.Command;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;

@SuppressWarnings("serial")
@Command
public class SendShipmentCommand implements Serializable {

    private final Key shipmentId;

    public SendShipmentCommand(Key shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Key getShipmentId() {
        return shipmentId;
    }
}
