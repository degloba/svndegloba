package com.degloba.events.api;

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

    private int versio = 1;

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
        this.versio = version;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getOccurredOn() {
		return occurredOn;
	}

	public void setOccurredOn(Date occurredOn) {
		this.occurredOn = occurredOn;
	}

	public int getVersio() {
		return versio;
	}

	public void setVersio(int versio) {
		this.versio = versio;
	}



}