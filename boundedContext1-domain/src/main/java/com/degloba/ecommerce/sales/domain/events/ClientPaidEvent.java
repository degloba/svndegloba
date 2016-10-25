package com.degloba.ecommerce.sales.domain.events;

import java.io.Serializable;

// Google app engine
//import com.google.appengine.api.datastore.Key;

// Domain
import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.ClientData;
import com.degloba.domain.sharedkernel.Money;

// Events
import com.degloba.event.annotations.Event;


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
