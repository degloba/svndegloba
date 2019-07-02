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


/**
 * @category Un client ha fet un pagament
 * 
 * @author degloba
 * 
 */

@Event
public class PagatClientEvent implements IDomainEvent<Object> {

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

	public boolean esIgualque(Object altraEvent) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String id() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date occurredOn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int version() {
		// TODO Auto-generated method stub
		return 0;
	}

}
