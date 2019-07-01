package com.degloba.ecommerce.enviaments.domain.events;

import java.io.Serializable;

import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
import com.degloba.domain.event.DomainEvent;
import com.degloba.event.annotations.Event;

/**
 * @category s'ha produit una entrega d'un enviament
 * 
 * @author degloba
 *
 */
@SuppressWarnings("serial")
@Event
public class EnviamentLliuratEvent extends DomainEvent {

    private final AggregateId enviamentId;

    public EnviamentLliuratEvent(AggregateId enviamentId) {
        this.enviamentId = enviamentId;
    }

    public AggregateId getEnviamentId() {
        return enviamentId;
    }
}
