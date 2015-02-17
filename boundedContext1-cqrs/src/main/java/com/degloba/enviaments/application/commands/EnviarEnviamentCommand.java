package com.degloba.enviaments.application.commands;

import java.io.Serializable;

import command.annotations.Command;
import com.degloba.domain.canonicalmodel.publishedlanguage.AggregateId;


@SuppressWarnings("serial")
@Command
public class EnviarEnviamentCommand implements Serializable {

    private final AggregateId shipmentId;

    public EnviarEnviamentCommand(AggregateId shipmentId) {
        this.shipmentId = shipmentId;
    }

    public AggregateId getShipmentId() {
        return shipmentId;
    }
}


