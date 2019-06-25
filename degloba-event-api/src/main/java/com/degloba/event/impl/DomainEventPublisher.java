package com.degloba.event.impl;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Spring
import org.springframework.stereotype.Component;

import com.degloba.event.api.IEvent;
import com.degloba.event.api.IEventHandler;
// Events
import com.degloba.event.application.IApplicationEventPublisher;
import com.degloba.event.domain.IDomainEvent;
import com.degloba.event.domain.IDomainEventPublisher;

/**
 * @category Publicador d'events de domini de tipus {@link IEvent}
 * <p/>
 * Els handlers estan en memoria ({@link HashSet})
 */
@Component
public class DomainEventPublisher<T extends IEvent> implements IDomainEventPublisher<IDomainEvent<T>>, IApplicationEventPublisher<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DomainEventPublisher.class);

    private Set<IEventHandler<T>> eventHandlers = new HashSet<IEventHandler<T>>();

    /**
     * 
     * @param handler
     * 
     * @category Afegeix un {@link IEventHandler} en la colecció {@link eventHandlers}
     */
    public void registerEventHandler(IEventHandler<T> handler) {
        eventHandlers.add(handler);
        // new SpringEventHandler(eventType, beanName, method));
    }
    
    public void publish(T event) {
        doPublish(event);
    }

    /**
     * 
     * @param event
     * 
     * @category Cerca dins la colecció {@link eventHandlers} un {@link IEventHandler} que pot gestionar (tractar) el 
     * {@link IEvent} i executa el seu mètode Handle
     */
    protected void doPublish(T event) {
        for (IEventHandler<T> handler : new ArrayList<IEventHandler<T>>(eventHandlers)) {
            if (handler.canHandle(event)) {
                try {
                    handler.handle(event);
                } catch (Exception e) {
                    LOGGER.error("event handling error", e);
                }
            }
        }
    }

	@Override
	public void publish(Object event) {
		// TODO Auto-generated method stub
		
	}
}
