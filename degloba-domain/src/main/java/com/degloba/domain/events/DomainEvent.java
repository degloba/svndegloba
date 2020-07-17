package com.degloba.domain.events;

import com.degloba.events.api.AbstractEvent;
import com.degloba.utils.Assert;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

	@Getter @Setter
	private String id = UUID.randomUUID().toString();

	@Getter
    private Date occurredOn = new Date();

	@Getter
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

}
