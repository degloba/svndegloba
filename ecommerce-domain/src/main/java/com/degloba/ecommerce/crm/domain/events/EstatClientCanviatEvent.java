package com.degloba.ecommerce.crm.domain.events;

import com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa.Client.EstatClient;
import com.degloba.events.annotations.EventAnnotation;
import com.degloba.events.api.AbstractEvent;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @category L'estat d'un client ha canviat
 * 
 * @author degloba
 *
 */
@EventAnnotation
@AllArgsConstructor
public class EstatClientCanviatEvent extends AbstractEvent {

    @Getter private final AggregateId clientId;
    @Getter private final EstatClient estatClient;


}
