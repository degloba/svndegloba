package com.degloba.infrastructure.events.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.degloba.IApplicationEventPublisher;
import com.degloba.domain.IDomainEvent;
import com.degloba.domain.IDomainEventPublisher;
import com.degloba.infrastructure.events.impl.handlers.EventHandler;

@Component
public class SimpleEventPublisher implements IDomainEventPublisher<IDomainEvent<Object>>, IApplicationEventPublisher<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEventPublisher.class);

    private Set<EventHandler> eventHandlers = new HashSet<EventHandler>();

    public void registerEventHandler(EventHandler handler) {
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
        for (EventHandler handler : new ArrayList<EventHandler>(eventHandlers)) {
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
