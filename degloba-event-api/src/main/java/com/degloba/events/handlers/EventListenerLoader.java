package com.degloba.events.handlers;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.degloba.events.api.AbstractEventListener;
import com.degloba.events.api.IEvent;
import com.degloba.events.bus.IEventBus;

import java.util.Set;

/**
 * @category Carrega per reflection {@link AbstractEventListener}S dins d'uns packages 
 * i els registra en un {@link IEventBus}
 * 
 *  @author degloba
 */
public class EventListenerLoader<T extends IEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventListenerLoader.class);

    /**
     *  {@link IEventBus}
     */
    private IEventBus<T> eventBus;

    private String[] packages;

    public EventListenerLoader(IEventBus<T> eventBus, String[] packages) {
        this.eventBus = eventBus;
        this.packages = packages;
    }

    /**
     * @category Cerca tots els {@link AbstractEventListener}S dins dels packages 
     * i els registra en {@link IEventBus}
     */
    public void execute() {
        for (String each : packages) {
            Reflections reflections = new Reflections(each);
            Set<Class<? extends AbstractEventListener>> handlers = reflections.getSubTypesOf(AbstractEventListener.class);
            for (Class<? extends AbstractEventListener> handler : handlers) {
                try {
                    eventBus.registra(handler.newInstance());
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
