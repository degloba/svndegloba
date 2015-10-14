package com.degloba.ecommerce.canonicalmodel.events;

import java.io.Serializable;

import com.degloba.annotations.event.Event;
import com.google.appengine.api.datastore.Key;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.ecommerce.crm.domain.Customer.CustomerStatus;

/**
 * @author degloba
 * 
 */
@SuppressWarnings("serial")
@Event
public class CustomerStatusChangedEvent implements Serializable {

    private final Key customerId;
    private final CustomerStatus status;

    public CustomerStatusChangedEvent(Key customerId, CustomerStatus status) {
        this.customerId = customerId;
        this.status = status;
    }

    public Key getCustomerId() {
        return customerId;
    }

    public CustomerStatus getStatus() {
        return status;
    }
}
