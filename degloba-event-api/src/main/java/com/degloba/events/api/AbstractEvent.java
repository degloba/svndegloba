package com.degloba.events.api;

import com.degloba.utils.Assert;

import lombok.Data;
import lombok.Value;

import java.util.Date;
import java.util.UUID;

/**
 * @author degloba
 * 
 * @category Event abstracte que implementa {@link IEvent}
 */
@Data
public abstract class AbstractEvent implements IEvent {
    
    private String id = UUID.randomUUID().toString();
    private Date dataEvent = new Date();
    private int versio = 1;

	public AbstractEvent() {
        this(new Date(), 1);
    }

    /**
     * @param occurredOn Tiempo de ocurrencia
     */
    public AbstractEvent(Date dataEvent) {
        this(dataEvent, 1);
    }

     public AbstractEvent(Date dataEvent, int versio) {
        Assert.notNull(dataEvent);
        this.dataEvent = new Date(dataEvent.getTime());
        this.versio = versio;
    }
}