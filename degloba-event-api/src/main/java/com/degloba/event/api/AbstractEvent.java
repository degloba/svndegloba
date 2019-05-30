package com.degloba.event.api;

import com.degloba.utils.Assert;

import java.util.Date;
import java.util.UUID;

/**
 * @author degloba
 * 
 * @category Event abstracte que implementa {@link IEvent}
 */
public abstract class AbstractEvent implements IEvent {

    private String id = UUID.randomUUID().toString();

    private Date occurredOn = new Date();

    private int version = 1;

    public AbstractEvent() {
        this(new Date(), 1);
    }

    /**
     * @param occurredOn Tiempo de ocurrencia
     */
    public AbstractEvent(Date occurredOn) {
        this(occurredOn, 1);
    }

     public AbstractEvent(Date occurredOn, int version) {
        Assert.notNull(occurredOn);
        this.occurredOn = new Date(occurredOn.getTime());
        this.version = version;
    }

     @Override
    public String id() {
        return id;
    }

    @Override
    public Date occurredOn() {
        return occurredOn;
    }

    @Override
    public int version() {
        return version;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AbstractEvent)) {
            return false;
        }
        AbstractEvent that = (AbstractEvent) other;
        return this.id().equals(that.id());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}