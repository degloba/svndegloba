package com.degloba.ecommerce.shipping.application.api.commands;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import com.degloba.cqrs.command.annotations.Command;


@SuppressWarnings("serial")
@Command
public class SendShipmentCommand implements Serializable {

    private final long shipmentId;

    public SendShipmentCommand(long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public long getShipmentId() {
        return shipmentId;
    }
}
