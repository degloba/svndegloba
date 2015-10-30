package com.degloba.event.api;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Analiza el controlador de eventos en el paquete especificado y lo registrar en el bus de eventos .
 */
public class EventListenerLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventListenerLoader.class);

    //Bus de eventos para los detectores de eventos de registro
    private IEventBus eventBus;

    // buscar detectores de eventos de estos paquetes , el registro en el bus de eventos
    private String[] packages;

    public EventListenerLoader(IEventBus eventBus, String[] packages) {
        this.eventBus = eventBus;
        this.packages = packages;
    }

    /**
     * Analiza el controlador de eventos en el paquete especificado y lo registrar en el bus de eventos .
     */
    public void execute() {
        for (String each : packages) {
            Reflections reflections = new Reflections(each);
            Set<Class<? extends AbstractEventListener>> handlers = reflections.getSubTypesOf(AbstractEventListener.class);
            for (Class<? extends IEventListener> handler : handlers) {
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
