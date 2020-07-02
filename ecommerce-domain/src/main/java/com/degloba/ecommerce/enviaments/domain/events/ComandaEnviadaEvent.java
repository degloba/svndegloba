package com.degloba.ecommerce.enviaments.domain.events;

import java.io.Serializable;
import java.util.Date;

import com.degloba.domain.events.DomainEvent;
import com.degloba.events.annotations.Event;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;

import lombok.Data;

/**
 * @category S'ha enviat una {@link Comanda}
 * 
 * @author degloba
 *
 */
@SuppressWarnings("serial")
@Event
@Data
public class ComandaEnviadaEvent extends DomainEvent {

    private final AggregateId comandaId;
    private final AggregateId enviamentId;

    public ComandaEnviadaEvent(AggregateId comandaId, AggregateId enviamentId) {
        this.comandaId = comandaId;
        this.enviamentId = enviamentId;
    }

	public AggregateId getComandaId() {
		// TODO Auto-generated method stub
		return null;
	}

}
