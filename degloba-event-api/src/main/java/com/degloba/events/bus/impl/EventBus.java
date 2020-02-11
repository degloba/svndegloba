package com.degloba.events.bus.impl;

import com.degloba.events.api.AbstractEventListener;
import com.degloba.events.api.IEvent;
import com.degloba.events.api.IEventListener;
import com.degloba.events.bus.IEventBus;
import com.degloba.events.persistence.IEventStore;
import com.degloba.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @category Bus d'events de tipus {@link IEvent}.</br> 
 * Conté : un {@link IEventStore} i una llista de {@link IEventListener}S
 * 
 * @author degloba
 */
public final class EventBus<T extends IEvent> implements IEventBus<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventBus.class);

    /**
     * Llista de {@link IEventListener}
     */
    private List<IEventListener<T>> listeners = new ArrayList<IEventListener<T>>();

    public EventBus(IEventStore<T> eventStore) {
    }

    public EventBus(IEventStore<T> eventStore, List<IEventListener<T>> listeners) {
        Assert.notNull(eventStore, "Event Store is null.");
        Assert.notEmpty(listeners, "listeners must not be null or empty.");
        this.listeners = Collections.unmodifiableList(listeners);
    }

    List<IEventListener<T>> getListeners() {
        return listeners;
    }

	@Override
	public void registra(AbstractEventListener<?> newInstance) {
		// TODO Auto-generated method stub
		
	}


}
