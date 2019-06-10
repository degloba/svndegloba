package com.degloba.ecommerce.vendes.domain.events;

import java.io.Serializable;

import com.degloba.persistence.rdbms.jpa.BaseEntity;
import com.degloba.persistence.domain.AggregateId;
// Domain
import com.degloba.persistence.domain.ClientData;
import com.degloba.persistence.domain.sharedkernel.Money;

// Events
import com.degloba.event.annotations.Event;


/**
 * @author degloba
 * 
 */
@SuppressWarnings("serial")
@Event
public class PagatClientEvent implements Serializable {

    private final AggregateId paymentId;
    private ClientData clientData;
    private Money amount;
    
    
    public PagatClientEvent(AggregateId paymentId, ClientData clientData, Money amount) {
        this.paymentId = paymentId;
        this.clientData = clientData;
        this.amount = amount;
    }

	public AggregateId getPaymentId() {
		return paymentId;
	}
	
	public ClientData getClientData() {
		return clientData;
	}
	
	public Money getAmount() {
		return amount;
	}
}
