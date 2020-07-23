package com.degloba.ecommerce.enviaments.domain.events;

import com.degloba.domain.events.DomainEvent;
import com.degloba.events.annotations.EventAnnotation;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;

/**
 * @category s'ha produit una entrega d'un enviament
 * 
 * @author degloba
 *
 */
@SuppressWarnings("serial")
@EventAnnotation
public class EnviamentLliuratEvent extends DomainEvent {

    private final AggregateId enviamentId;

    public EnviamentLliuratEvent(AggregateId enviamentId) {
        this.enviamentId = enviamentId;
    }

    public AggregateId getEnviamentId() {
        return enviamentId;
    }
}
