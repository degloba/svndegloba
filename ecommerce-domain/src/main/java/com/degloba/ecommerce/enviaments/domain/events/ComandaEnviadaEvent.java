package com.degloba.ecommerce.enviaments.domain.events;

import com.degloba.domain.events.DomainEvent;
import com.degloba.events.annotations.Event;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @category S'ha enviat una {@link Comanda}
 * 
 * @author degloba
 *
 */
@SuppressWarnings("serial")
@Event
@Data
@AllArgsConstructor
//@NoArgsConstructor
public class ComandaEnviadaEvent extends DomainEvent {

    private final AggregateId comandaId;
    private final AggregateId enviamentId;


	public AggregateId getComandaId() {
		// TODO Auto-generated method stub
		return null;
	}

}
