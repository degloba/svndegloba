package com.degloba.domain.event;

import com.google.common.eventbus.EventBus;
import org.dayatang.utils.Assert;

/**
 * Created by yyang on 14-10-4.
 */
public class DomainEventBusImpl implements DomainEventBus {

    private EventBus eventBus;

    private EventStore eventStore;

    public DomainEventBusImpl(EventBus eventBus, EventStore eventStore) {
        Assert.notNull(eventBus, "EventBus is null!");
        Assert.notNull(eventStore, "EventStore is null!");
        this.eventBus = eventBus;
        this.eventStore = eventStore;
    }
    

    public void publishEvent(DomainEvent event) {
        eventBus.post(event);
        eventStore.append(event);
    }

    public void registerSubscriber(Object subscriber) {
        eventBus.register(subscriber);
    }

    public void unregisterSubscriber(Object subscriber) {
        eventBus.unregister(subscriber);
    }


}
