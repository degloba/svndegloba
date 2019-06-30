package com.degloba.ecommerce.enviaments.domain.events;

import java.io.Serializable;

import com.degloba.domain.event.DomainEvent;
import com.degloba.event.annotations.Event;

/**
 * @category
 * 
 * @author degloba
 *
 */
@SuppressWarnings("serial")
@Event
public class ComandaEnviadaEvent extends DomainEvent {

    private final long comandaId;
    private final long enviamentId;

    public ComandaEnviadaEvent(long comandaId, long enviamentId) {
        this.comandaId = comandaId;
        this.enviamentId = enviamentId;
    }

    public long getComandaId() {
        return comandaId;
    }

    public long getEnviamentId() {
        return enviamentId;
    }

	public boolean sameEventAs(Object altraEvent) {
		// TODO Auto-generated method stub
		return false;
	}
}
