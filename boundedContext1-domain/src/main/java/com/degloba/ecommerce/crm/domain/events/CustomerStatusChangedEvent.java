package com.degloba.ecommerce.crm.domain.events;

import java.io.Serializable;

import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa.Customer.CustomerStatus;

// Event
import com.degloba.event.annotations.Event;


@SuppressWarnings("serial")
@Event
public class CustomerStatusChangedEvent implements Serializable {

    private final AggregateId customerId;
    private final CustomerStatus status;

    public CustomerStatusChangedEvent(AggregateId customerId, CustomerStatus status) {
        this.customerId = customerId;
        this.status = status;
    }

    public AggregateId getCustomerId() {
        return customerId;
    }

    public CustomerStatus getStatus() {
        return status;
    }
}
