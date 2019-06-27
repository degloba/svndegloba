package com.degloba.ecommerce.crm.domain.events;

import java.io.Serializable;


import com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa.Client.EstatClient;

// Event
import com.degloba.event.annotations.Event;
import com.degloba.persistence.domain.AggregateId;


@SuppressWarnings("serial")
@Event
public class CustomerStatusChangedEvent implements Serializable {

    private final AggregateId customerId;
    private final EstatClient status;

    public CustomerStatusChangedEvent(AggregateId customerId, EstatClient status) {
        this.customerId = customerId;
        this.status = status;
    }

    public AggregateId getCustomerId() {
        return customerId;
    }

    public EstatClient getStatus() {
        return status;
    }
}
