package com.degloba.ecommerce.canonicalmodel.events;

import java.io.Serializable;

import com.degloba.ecommerce.crm.domain.Customer.CustomerStatus;

// Event
import com.degloba.event.annotations.Event;


@SuppressWarnings("serial")
@Event
public class CustomerStatusChangedEvent implements Serializable {

    private final long customerId;
    private final CustomerStatus status;

    public CustomerStatusChangedEvent(long customerId, CustomerStatus status) {
        this.customerId = customerId;
        this.status = status;
    }

    public long getCustomerId() {
        return customerId;
    }

    public CustomerStatus getStatus() {
        return status;
    }
}
