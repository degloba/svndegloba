package com.degloba.event.api;

/**
 * Interfaz de bus de eventos. 
 * Llama a los controladores de eventos adecuadas para manejar estos eventos cuando se reciben en el sector eventos .
 */
public interface IEventBus {

    /**
     * Registra controladores de eventos
     * @param handlers Para registrar los manejadores de eventos
     */
    void register(IEventListener... handlers);

    void unregister(IEventListener... handlers);

    void post(IEvent event);

}
