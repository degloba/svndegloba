package com.degloba.ecommerce.vendes.domain.events;

import java.io.Serializable;
import java.util.Date;

import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;
import com.degloba.persistence.rdbms.api.jpa.BaseEntity;
import com.degloba.persistence.rdbms.api.jpa.ClientData;
import com.degloba.domain.events.DomainEvent;
import com.degloba.events.annotations.Event;
import com.degloba.events.api.AbstractEvent;
import com.degloba.events.api.IDomainEvent;

/**
 * @category Un client ha fet un pagament
 * 
 * @author degloba
 * 
 */
@Event
public class PagatClientEvent extends DomainEvent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
