package com.degloba.event.impl;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.degloba.event.api.AbstractEventListener;
import com.degloba.event.api.IEvent;
import com.degloba.event.api.IEventBus;

import java.util.Set;

/**
 * Classe : Carrega per reflection {@link AbstractEventListener}S dins d'uns packages i els registra en un {@link IEventBus} 
 */
public class EventListenerLoader<T extends IEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventListenerLoader.class);

    // {@link IEventBus}
    private IEventBus<?> eventBus;

    private String[] packages;

    public EventListenerLoader(IEventBus<?> eventBus, String[] packages) {
        this.eventBus = eventBus;
        this.packages = packages;
    }

    /**
     * Cerca tots els {@link AbstractEventListener}S dins dels packages i els registra en {@link IEventBus}
     */
    public void execute() {
        for (String each : packages) {
            Reflections reflections = new Reflections(each);
            Set<Class<? extends AbstractEventListener>> handlers = reflections.getSubTypesOf(AbstractEventListener.class);
            for (Class<? extends AbstractEventListener> handler : handlers) {
                try {
                    eventBus.register(handler.newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                    LOGGER.error("Handler " + handler + " create failed!", e);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    LOGGER.error("Handler " + handler + " create failed!", e);
                }
            }
        }
    }
}
