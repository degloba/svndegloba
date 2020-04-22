package com.degloba.events.bus.impl;

import com.degloba.events.api.AbstractEventListener;
import com.degloba.events.api.IEvent;
import com.degloba.events.api.IEventListener;
import com.degloba.events.bus.IEventBus;
import com.degloba.events.persistence.IEventStore;
import com.degloba.utils.Assert;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @category Bus d'events de tipus {@link IEvent}.</br> 
 * Conté : un {@link IEventStore} i una llista de {@link IEventListener}S
 * 
 * @author degloba
 */
@Slf4j
public final class EventBus<T extends IEvent> implements IEventBus<T> {

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
