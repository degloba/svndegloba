package com.degloba.domain.event;

import com.google.common.eventbus.EventBus;

import com.degloba.event.api.IEvent;
import com.degloba.event.api.IEventListener;
import com.degloba.utils.Assert;

public class DomainEventBusImpl implements IDomainEventBus {

    private EventBus eventBus;

    private IStoredDomainEventRepository storedEventRepository;

    public DomainEventBusImpl(EventBus eventBus, IStoredDomainEventRepository storedEventRepository) {
        Assert.notNull(eventBus, "EventBus is null!");
        Assert.notNull(storedEventRepository, "EventStore is null!");
        this.eventBus = eventBus;
        this.storedEventRepository = storedEventRepository;
    }
    

    public void publishEvent(ADomainEvent event) {
        eventBus.post(event);
        storedEventRepository.append(event);
    }

    public void registerSubscriber(Object subscriber) {
        eventBus.register(subscriber);
    }

    public void unregisterSubscriber(Object subscriber) {
        eventBus.unregister(subscriber);
    }


	@Override
	public void register(IEventListener<?>... handlers) {
		eventBus.register(handlers);		
	}


	@Override
	public void post(IEvent event) {
		eventBus.post(event);
	}


	@Override
	public void unregister(IEventListener<?>... handlers) {
		eventBus.unregister(handlers);		
	}

}
