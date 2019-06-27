package com.degloba.ecommerce.vendes.domain.events;

import java.io.Serializable;

import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
// Events
import com.degloba.event.annotations.Event;


@SuppressWarnings("serial")
@Event
public class OrdreEnviadaEvent implements Serializable{

	private AggregateId comandaId;
	
	public OrdreEnviadaEvent(AggregateId comandaId){
		this.comandaId = comandaId;
	}
	
	public AggregateId getComandaId() {
		return comandaId;
	}

}
