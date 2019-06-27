package com.degloba.ecommerce.enviaments.domain.events;

import java.io.Serializable;

import com.degloba.event.annotations.Event;

/**
 * @category
 * 
 * @author degloba
 *
 */
@SuppressWarnings("serial")
@Event
public class OrdreEnviadaEvent implements Serializable {

    private final long comandaId;
    private final long enviamentId;

    public OrdreEnviadaEvent(long comandaId, long enviamentId) {
        this.comandaId = comandaId;
        this.enviamentId = enviamentId;
    }

    public long getComandaId() {
        return comandaId;
    }

    public long getEnviamentId() {
        return enviamentId;
    }
}
