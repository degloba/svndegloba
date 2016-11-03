package com.degloba.domain.event;

import com.google.common.eventbus.EventBus;

import java.util.Date;

import com.degloba.utils.Assert;

public class DomainEventBusImpl implements IDomainEventBus {

    private EventBus eventBus;

    private IStoredEventRepository storedEventRepository;

    public DomainEventBusImpl(EventBus eventBus, IStoredEventRepository storedEventRepository) {
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
	public String id() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Date occurredOn() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int version() {
		// TODO Auto-generated method stub
		return 0;
	}


}
