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
public class DomainEventBusImpl<T extends IEvent> implements IDomainEventBus<T> {

	@Inject
    private EventBus eventBus;

    @Inject
    private IStoredDomainEventMongoRepository storedDomainEventRepository;

    
    public DomainEventBusImpl() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DomainEventBusImpl(EventBus eventBus, IStoredDomainEventMongoRepository storedDomainEventRepository) {
        Assert.notNull(eventBus, "EventBus is null!");
        Assert.notNull(storedDomainEventRepository, "EventStore is null!");
        this.eventBus = eventBus;
        this.storedDomainEventRepository = storedDomainEventRepository;
    }
    
	

    public DomainEventBusImpl(IStoredDomainEventMongoRepository storedDomainEventRepository) {
		super();
		this.storedDomainEventRepository = storedDomainEventRepository;
	}


	public void publishEvent(DomainEvent event) {
        eventBus.post(event);
        StoredDomainEventMongoDb storedDomainEvent = storedDomainEventRepository.append(event);
        
        storedDomainEventRepository.insert(storedDomainEvent);
    }

    public void registerSubscriber(Object subscriber) {
        eventBus.register(subscriber);
    }

    public void unregisterSubscriber(Object subscriber) {
        eventBus.unregister(subscriber);
    }


	@Override
	public void register(IEventListener<T> handlers) {
		eventBus.register(handlers);		
	}


	@Override
	public void post(IEvent event) {
		eventBus.post(event);
	}


	@Override
	public void unregister(IEventListener<T> handlers) {
		eventBus.unregister(handlers);		
	}


	public IStoredDomainEventMongoRepository getStoredDomainEventRepository() {
		return storedDomainEventRepository;
	}


	public void setStoredDomainEventRepository(IStoredDomainEventMongoRepository storedDomainEventRepository) {
		this.storedDomainEventRepository = storedDomainEventRepository;
	}


	public EventBus getEventBus() {
		return eventBus;
	}


	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
	}


}
