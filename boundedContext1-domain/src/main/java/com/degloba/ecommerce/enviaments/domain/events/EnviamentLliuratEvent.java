package com.degloba.ecommerce.enviaments.domain.events;

import java.io.Serializable;

import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
import com.degloba.event.annotations.Event;


@SuppressWarnings("serial")
@Event
public class EnviamentLliuratEvent implements Serializable {

    private final AggregateId shipmentId;

    public EnviamentLliuratEvent(AggregateId shipmentId) {
        this.shipmentId = shipmentId;
    }

    public AggregateId getShipmentId() {
        return shipmentId;
    }
}
