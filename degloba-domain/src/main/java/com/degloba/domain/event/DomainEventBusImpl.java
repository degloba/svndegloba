package com.degloba.domain.event;

import com.google.common.eventbus.EventBus;

import javax.inject.Inject;

import com.degloba.event.api.IEvent;
import com.degloba.event.api.IEventListener;
import com.degloba.event.domain.IDomainEvent;
import com.degloba.event.persistence.IStoredDomainEvent;
import com.degloba.utils.Assert;


/**
 * @author degloba
 * 
 * @category 
 * 
 * <ul>
 * <li>
 * 		Implementa un Bus d'events d'entitats de domini amb Google Guava<br>
 * </li>
 * <li>
 * 		Guarda l'event en un repositori d'events implementat amb MongoDB
 * </li>
 * </ul>
 * 
 */
public class DomainEventBusImpl<T extends IEvent> implements IDomainEventBus<T> {

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

/**
 * (non-Javadoc)
 * @see com.degloba.domain.event.IDomainEventBus#publishEvent(com.degloba.domain.event.DomainEvent)
 */
	public void publishEvent(IStoredDomainEvent event) {
        eventBus.post(event);
        
        storedDomainEventRepository.append(event);        
    }
	

	@Override
	public void publishEvent(DomainEvent event) {
		// TODO Auto-generated method stub
		
	}
	
/*
 * (non-Javadoc)
 * @see com.degloba.domain.event.IDomainEventBus#registerSubscriber(java.lang.Object)
 */
    public void registerSubscriber(Object subscriber) {
        eventBus.register(subscriber);
    }
/*
 * (non-Javadoc)
 * @see com.degloba.domain.event.IDomainEventBus#unregisterSubscriber(java.lang.Object)
 */
    public void unregisterSubscriber(Object subscriber) {
        eventBus.unregister(subscriber);
    }

/*
 * (non-Javadoc)
 * @see com.degloba.event.api.IEventBus#register(com.degloba.event.api.IEventListener)
 */
	@Override
	public void register(IEventListener<T> handlers) {
		eventBus.register(handlers);		
	}

/*
 * (non-Javadoc)
 * @see com.degloba.event.api.IEventBus#post(com.degloba.event.api.IEvent)
 */
	@Override
	public void post(IEvent event) {
		eventBus.post(event);
	}

/*
 * (non-Javadoc)
 * @see com.degloba.event.api.IEventBus#unregister(com.degloba.event.api.IEventListener)
 */
	@Override
	public void unregister(IEventListener<T> handlers) {
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
