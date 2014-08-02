package com.degloba.canonicalmodel.events;

import com.degloba.boundedContext.domain.Modalpanel.ModalpanelStatus;

public class ModalpanelStatusChangedEvent<K> {

	private final K customerId;
    private final ModalpanelStatus status;

    public ModalpanelStatusChangedEvent(K customerId, ModalpanelStatus status) {
        this.customerId = customerId;
        this.status = status;
    }

    public K getCustomerId() {
        return customerId;
    }

    public ModalpanelStatus getStatus() {
        return status;
    }
}
