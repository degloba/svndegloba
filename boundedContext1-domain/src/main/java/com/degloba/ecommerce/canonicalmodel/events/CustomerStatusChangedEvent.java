package com.degloba.ecommerce.canonicalmodel.events;

import java.io.Serializable;

// Google app engine
import com.google.appengine.api.datastore.Key;

import com.degloba.ecommerce.crm.domain.Customer.CustomerStatus;

// Event
import com.degloba.event.annotations.Event;


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
