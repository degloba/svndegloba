package com.degloba.event.api;

import org.dayatang.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Bus de Eventos
 */
public final class SimpleEventBus implements IEventBus {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEventBus.class);

    private IEventStore eventStore;

    private List<IEventListener> listeners = new ArrayList<IEventListener>();

    public SimpleEventBus(IEventStore eventStore) {
        this.eventStore = eventStore;
    }

    public SimpleEventBus(IEventStore eventStore, List<IEventListener> listeners) {
        Assert.notNull(eventStore, "Event Store is null.");
        this.eventStore = eventStore;
        Assert.notEmpty(listeners, "listeners must not be null or empty.");
        this.listeners = Collections.unmodifiableList(listeners);
    }

    List<IEventListener> getListeners() {
        return listeners;
    }

    @Override
    public void register(IEventListener... listeners) {
        this.listeners.addAll(Arrays.asList(listeners));
    }

    @Override
    public void unregister(IEventListener... listeners) {
        this.listeners.removeAll(Arrays.asList(listeners));
    }

    @Override
    public void post(IEvent event) {
        eventStore.store(event);
        for (IEventListener listener : listeners) {
            listener.onEvent(event);
        }
    }
}
