package com.degloba.ecommerce.crm.domain.events;

import java.io.Serializable;

import com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa.Client.EstatClient;
import com.degloba.events.annotations.Event;
import com.degloba.events.api.AbstractEvent;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;

import lombok.Data;
import lombok.Getter;
import lombok.Value;

/**
 * @category L'estat d'un client ha canviat
 * 
 * @author degloba
 *
 */
@Event
public class EstatClientCanviatEvent extends AbstractEvent {

    @Getter private final AggregateId clientId;
    @Getter private final EstatClient estatClient;

    public EstatClientCanviatEvent(AggregateId clientId, EstatClient estatClient) {
        this.clientId = clientId;
        this.estatClient = estatClient;
    }

}
