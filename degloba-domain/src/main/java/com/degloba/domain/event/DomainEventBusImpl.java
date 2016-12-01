package com.degloba.domain.event;

import com.google.common.eventbus.EventBus;

import javax.inject.Inject;

import com.degloba.event.api.IEvent;
import com.degloba.event.api.IEventListener;
import com.degloba.utils.Assert;


/*
 * 1.- Implementa "EventBus" con Google Guava
 * 2.- Guarda el evento "publish" en un repositorio de Eventos
 * 
 */
public class DomainEventBusImpl implements IDomainEventBus {

	@Inject
    private EventBus eventBus;

    @Inject
    private IStoredDomainEventRepository storedDomainEventRepository;

    
    public DomainEventBusImpl() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DomainEventBusImpl(EventBus eventBus, IStoredDomainEventRepository storedDomainEventRepository) {
        Assert.notNull(eventBus, "EventBus is null!");
        Assert.notNull(storedDomainEventRepository, "EventStore is null!");
        this.eventBus = eventBus;
        this.storedDomainEventRepository = storedDomainEventRepository;
    }
    
	

    public DomainEventBusImpl(IStoredDomainEventRepository storedDomainEventRepository) {
		super();
		this.storedDomainEventRepository = storedDomainEventRepository;
	}


	public void publishEvent(ADomainEvent event) {
        eventBus.post(event);
        StoredDomainEvent storedDomainEvent = storedDomainEventRepository.append(event);
        
        storedDomainEventRepository.insert(storedDomainEvent);
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


	public IStoredDomainEventRepository getStoredDomainEventRepository() {
		return storedDomainEventRepository;
	}


	public void setStoredDomainEventRepository(IStoredDomainEventRepository storedDomainEventRepository) {
		this.storedDomainEventRepository = storedDomainEventRepository;
	}


	public EventBus getEventBus() {
		return eventBus;
	}


	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
	}

}
