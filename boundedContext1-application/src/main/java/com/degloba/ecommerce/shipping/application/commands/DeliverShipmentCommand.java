package com.degloba.ecommerce.shipping.application.commands;

import java.io.Serializable;

import com.degloba.cqrs.command.annotations.Command;
import com.degloba.persistence.domain.AggregateId;



@SuppressWarnings("serial")
@Command
public class DeliverShipmentCommand implements Serializable {

    private final AggregateId shipmentId;

    public DeliverShipmentCommand(AggregateId shipmentId) {
        this.shipmentId = shipmentId;
    }

    public AggregateId getShipmentId() {
        return shipmentId;
    }
}
