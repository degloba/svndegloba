package com.degloba.domain.events;

import com.google.common.eventbus.EventBus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;

import com.degloba.events.api.AbstractEventListener;
import com.degloba.events.api.IDomainEvent;
import com.degloba.events.api.IEvent;
import com.degloba.events.api.IEventListener;
import com.degloba.events.persistence.IDomainEventStore;

import com.degloba.utils.Assert;


/**
 * @author degloba
 * 
 * @category  EventBus
 * 
 * @implNote Implementa un bus d'events d'entitats de domini amb Google Guava
 * 
 * @implSpec Guarda l'event en un repositori d'events implementat amb MongoDB
 * 
 * 
 */
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class DomainEventBus<T extends IEvent> implements IDomainEventBus<T> {

	@Autowired
    private EventBus eventBus;

	@Autowired
    private IStoredDomainEventRepository storedDomainEventRepository;


	public DomainEventBus(@NonNull EventBus eventBus, @NonNull IStoredDomainEventRepository storedDomainEventRepository) {
        this.eventBus = eventBus;
        this.storedDomainEventRepository = storedDomainEventRepository;
    }
    	

    public DomainEventBus(IStoredDomainEventRepository storedDomainEventRepository) {
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
    public void unRegisterSubscriber(Object subscriber) {
        eventBus.unregister(subscriber);
    }


	@Override
	public void registra(AbstractEventListener<?> newInstance) {
		// TODO Auto-generated method stub
		
	}

}
