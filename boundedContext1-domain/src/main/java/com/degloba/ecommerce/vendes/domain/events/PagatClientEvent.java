package com.degloba.ecommerce.vendes.domain.events;

import java.io.Serializable;
import java.util.Date;

import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
import com.degloba.persistence.rdbms.jpa.ClientData;
import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.domain.event.DomainEvent;
import com.degloba.event.annotations.Event;
import com.degloba.event.api.AbstractEvent;
import com.degloba.event.domain.IDomainEvent;
import com.degloba.domain.event.DomainEvent;

/**
 * @category Un client ha fet un pagament
 * 
 * @author degloba
 * 
 */
@Event
public class PagatClientEvent extends DomainEvent {

    private final AggregateId pagamentId;
    private ClientData clientData;
    private Money quantitat;
    
    
    public PagatClientEvent(AggregateId pagamentId, ClientData clientData, Money quantitat) {
        this.pagamentId = pagamentId;
        this.clientData = clientData;
        this.quantitat = quantitat;
    }

	public AggregateId getPagamentId() {
		return pagamentId;
	}
	
	public ClientData getClientData() {
		return clientData;
	}
	
	public Money getQuantitat() {
		return quantitat;
	}

}
