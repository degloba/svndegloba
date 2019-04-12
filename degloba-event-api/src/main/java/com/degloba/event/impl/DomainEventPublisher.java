package com.degloba.event.impl;

import java.io.Serializable;
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
 * Component : DomainEventPublisher de tipus d'event {@link IEvent}
 */
@Component
public class DomainEventPublisher<T extends IEvent> implements IDomainEventPublisher<IDomainEvent<T>>, IApplicationEventPublisher<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DomainEventPublisher.class);

    private Set<IEventHandler<T>> eventHandlers = new HashSet<IEventHandler<T>>();

    public void registerEventHandler(IEventHandler<T> handler) {
        eventHandlers.add(handler);
        // new SpringEventHandler(eventType, beanName, method));
    }


    public void publish(T event) {
        doPublish(event);
    }

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

	public void publish(Object event) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void publish(IDomainEvent<T> event) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void publish(Serializable event) {
		// TODO Auto-generated method stub
		
	}

}
