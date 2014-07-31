package com.degloba.canonicalmodel.events;

import domain.canonicalmodel.publishedlanguage.AggregateId;

import com.degloba.boundedContext.domain.Modalpanel.ModalpanelStatus;

public class ModalpanelStatusChangedEvent {

	private final AggregateId customerId;
    private final ModalpanelStatus status;

    public ModalpanelStatusChangedEvent(AggregateId customerId, ModalpanelStatus status) {
        this.customerId = customerId;
        this.status = status;
    }

    public AggregateId getCustomerId() {
        return customerId;
    }

    public ModalpanelStatus getStatus() {
        return status;
    }
}
