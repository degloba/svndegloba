package com.degloba.domain.events;

import com.degloba.events.api.AbstractEvent;
import com.degloba.utils.Assert;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author degloba
 * 
 * @category Event associat a una entitat de domini
 */
public abstract class DomainEvent extends AbstractEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id = UUID.randomUUID().toString();

    private Date occurredOn = new Date();

    private int version = 1;

    public DomainEvent() {
        this(new Date(), 1);
    }

    /**
     *
     * @param occurredOn Temps d’ocurrència
     */
    public DomainEvent(Date occurredOn) {
        this(occurredOn, 1);
    }

    /**
     *
     * @param occurredOn Temps d’ocurrència
     * @param version Versió
     */
    public DomainEvent(Date occurredOn, int version) {
        Assert.notNull(occurredOn);
        this.occurredOn = new Date(occurredOn.getTime());
        this.version = version;
    }

    /**
     * Obté l'identificador de l'event
     * @return ID de l'event
     */
    public String getId() {
        return id;
    }

    /**
     * For test only
     * @param id Configura l'ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obté el temps d'ocurrència
     * @return Event time
     */
    public Date getOccurredOn() {
        return occurredOn;
    }

    /**
     * Obté la versió
     * @return Version of events
     */
    public int getVersion() {
        return version;
    }


}
