package com.degloba.ecommerce.crm.domain.events;

import java.io.Serializable;

import com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa.Client.EstatClient;

import com.degloba.event.annotations.Event;
import com.degloba.event.api.AbstractEvent;
import com.degloba.persistence.rdbms.jpa.AggregateId;

/**
 * @category L'estat d'un client ha canviat
 * 
 * @author degloba
 *
 */
@Event
public class EstatClientCanviatEvent extends AbstractEvent {

    private final AggregateId clientId;
    private final EstatClient estatClient;

    public EstatClientCanviatEvent(AggregateId clientId, EstatClient estatClient) {
        this.clientId = clientId;
        this.estatClient = estatClient;
    }

    public AggregateId getClientId() {
        return clientId;
    }

    public EstatClient getEstatClisent() {
        return estatClient;
    }
}
