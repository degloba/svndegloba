package com.degloba.ecommerce.canonicalmodel.events;

import java.io.Serializable;

import com.degloba.annotations.event.Event;
import com.google.appengine.api.datastore.Key;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.domain.canonicalmodel.publishedlanguage.ClientData;
import com.degloba.domain.sharedkernel.Money;

/**
 * @author degloba
 * 
 */
@SuppressWarnings("serial")
@Event
public class ClientPaidEvent implements Serializable {

    private final Key paymentId;
    private ClientData clientData;
    private Money amount;
    
    
    public ClientPaidEvent(Key paymentId, ClientData clientData, Money amount) {
        this.paymentId = paymentId;
        this.clientData = clientData;
        this.amount = amount;
    }

	public Key getPaymentId() {
		return paymentId;
	}
	
	public ClientData getClientData() {
		return clientData;
	}
	
	public Money getAmount() {
		return amount;
	}
}
