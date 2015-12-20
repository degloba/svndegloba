package com.degloba.event.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Spring
import org.springframework.stereotype.Component;

// Events
import com.degloba.event.application.IApplicationEventPublisher;
import com.degloba.event.domain.IDomainEvent;
import com.degloba.event.domain.IDomainEventPublisher;
import com.degloba.event.impl.handlers.IEventHandler;


@Component
public class SimpleEventPublisher implements IDomainEventPublisher<IDomainEvent<Object>>, IApplicationEventPublisher<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEventPublisher.class);

    private Set<IEventHandler> eventHandlers = new HashSet<IEventHandler>();

    public void registerEventHandler(IEventHandler handler) {
        eventHandlers.add(handler);
        // new SpringEventHandler(eventType, beanName, method));
    }

    public void publish(Serializable event) {
        doPublish(event);
    }

    public void publish(IDomainEvent<Object> event) {
        doPublish(event);
    }

    protected void doPublish(Object event) {
        for (IEventHandler handler : new ArrayList<IEventHandler>(eventHandlers)) {
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

}
