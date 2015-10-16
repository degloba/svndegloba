package com.degloba.ecommerce.shipping.application.api.commands;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import command.annotations.Command;


@SuppressWarnings("serial")
@Command
public class DeliverShipmentCommand implements Serializable {

    private final Key shipmentId;

    public DeliverShipmentCommand(Key shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Key getShipmentId() {
        return shipmentId;
    }
}
