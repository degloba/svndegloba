/**
 * 
 */
package com.insacosa.domain.events;

import ddd.domain.DomainEvent;

import com.insacosa.domain.Customer.CustomerStatus;

/**
 * @author Slawek
 * 
 */
@SuppressWarnings("serial")
public class CustomerStatusChangedEvent implements DomainEvent {

    private final Long customerId;
    private final CustomerStatus status;

    public CustomerStatusChangedEvent(Long customerId, CustomerStatus status) {
        this.customerId = customerId;
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public CustomerStatus getStatus() {
        return status;
    }
}
