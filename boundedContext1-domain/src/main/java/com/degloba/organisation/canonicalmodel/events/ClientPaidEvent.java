package com.degloba.organisation.canonicalmodel.events;

import java.io.Serializable;

import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.ClientData;

// Domain

import com.degloba.domain.sharedkernel.Money;

// Event
import com.degloba.event.annotations.Event;

// Google app engine
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 * 
 */
@SuppressWarnings("serial")
@Event
public class ClientPaidEvent implements Serializable {

    private final long paymentId;
    private ClientData clientData;
    private Money amount;
    
    
    public ClientPaidEvent(long paymentId, ClientData clientData, Money amount) {
        this.paymentId = paymentId;
        this.clientData = clientData;
        this.amount = amount;
    }

	public long getPaymentId() {
		return paymentId;
	}
	
	public ClientData getClientData() {
		return clientData;
	}
	
	public Money getAmount() {
		return amount;
	}
}
