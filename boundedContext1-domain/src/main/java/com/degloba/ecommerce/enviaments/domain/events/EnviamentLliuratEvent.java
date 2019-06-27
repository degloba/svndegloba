package com.degloba.ecommerce.enviaments.domain.events;

import java.io.Serializable;

import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
import com.degloba.event.annotations.Event;


@SuppressWarnings("serial")
@Event
public class EnviamentLliuratEvent implements Serializable {

    private final AggregateId enviamentId;

    public EnviamentLliuratEvent(AggregateId enviamentId) {
        this.enviamentId = enviamentId;
    }

    public AggregateId getEnviamentId() {
        return enviamentId;
    }
}
