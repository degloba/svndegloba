package com.degloba.event.api;

/**
 * Almacenamiento de Eventos
 */
public interface IEventStore {

    void store(IEvent event);
}
