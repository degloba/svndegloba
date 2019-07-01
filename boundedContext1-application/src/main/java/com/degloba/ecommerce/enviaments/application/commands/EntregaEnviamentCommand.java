package com.degloba.ecommerce.enviaments.application.commands;

import java.io.Serializable;

import com.degloba.cqrs.command.annotations.Command;
import com.degloba.persistence.domain.AggregateId;



@SuppressWarnings("serial")
@Command
public class DeliverShipmentCommand implements Serializable {

    private final AggregateId enviamentId;

    public DeliverShipmentCommand(AggregateId enviamentId) {
        this.enviamentId = enviamentId;
    }

    public AggregateId getEnviamentId() {
        return enviamentId;
    }
}
