package com.degloba.ecommerce.enviaments.domain.events;

import java.io.Serializable;

import com.degloba.domain.events.DomainEvent;
import com.degloba.events.annotations.Event;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;
import com.degloba.persistence.rdbms.api.jpa.BaseEntity;

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
