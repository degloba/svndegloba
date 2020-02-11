package com.degloba.domain.events;

import com.google.common.eventbus.EventBus;

import javax.inject.Inject;

import com.degloba.events.api.AbstractEventListener;
import com.degloba.events.api.IDomainEvent;
import com.degloba.events.api.IEvent;
import com.degloba.events.api.IEventListener;
import com.degloba.events.persistence.IDomainEventStore;
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
 * @see com.degloba.domain.events.IDomainEventBus#publicaEvent(com.degloba.domain.events.DomainEvent)
 */
	public void publicaEvent(DomainEvent event) {
        eventBus.post(event);
        
        storedDomainEventRepository.append(event);        
    }


/*
 * (non-Javadoc)
 * @see com.degloba.domain.event.IDomainEventBus#registerSubscriber(java.lang.Object)
 */
    public void registreSubscriber(Object subscriber) {
        eventBus.register(subscriber);
    }
/*
 * (non-Javadoc)
 * @see com.degloba.domain.event.IDomainEventBus#unregisterSubscriber(java.lang.Object)
 */
    public void unregisterSubscriber(Object subscriber) {
        eventBus.unregister(subscriber);
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


	@Override
	public void registra(AbstractEventListener<?> newInstance) {
		// TODO Auto-generated method stub
		
	}




}
