package com.degloba.event.api;

/**
 * Detector de eventos  
 * Toda clase de implementación debe tener un valor predeterminado ningún argumento constructo * 
 */
public interface IEventListener<T extends IEvent> {

    void onEvent(T event);

}
