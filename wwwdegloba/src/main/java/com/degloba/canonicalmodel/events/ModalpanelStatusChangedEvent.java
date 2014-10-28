package com.degloba.canonicalmodel.events;

import com.degloba.boundedContext.domain.modalpanel.Modalpanel.ModalpanelStatus;

public class ModalpanelStatusChangedEvent<K> {

	private final K modalPanelId;
    private final ModalpanelStatus status;

    public ModalpanelStatusChangedEvent(K modalPanelId, ModalpanelStatus status) {
        this.modalPanelId = modalPanelId;
        this.status = status;
    }

    public K getModalPanelId() {
        return modalPanelId;
    }

    public ModalpanelStatus getStatus() {
        return status;
    }
}
