package com.degloba.event.api;

/**
 * Interfície : Magatzem d'events
 */
public interface IEventStore {

    void store(IEvent event);
}
