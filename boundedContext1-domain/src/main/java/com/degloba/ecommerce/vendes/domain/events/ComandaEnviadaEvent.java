package com.degloba.ecommerce.vendes.domain.events;

import java.io.Serializable;
import java.util.Date;

import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
import com.degloba.domain.event.DomainEvent;
import com.degloba.event.annotations.Event;
import com.degloba.event.api.AbstractEvent;
import com.degloba.event.domain.IDomainEvent;

/**
 * @category Comanda enviada
 * 
 * @author degloba
 *
 */
@SuppressWarnings("serial")
@Event
public class ComandaEnviadaEvent extends DomainEvent  {

	private AggregateId comandaId;
	
	public ComandaEnviadaEvent(AggregateId comandaId){
		this.comandaId = comandaId;
	}
	
	public AggregateId getComandaId() {
		return comandaId;
	}

	public boolean sameEventAs(Object altraEvent) {
		// TODO Auto-generated method stub
		return false;
	}



}
