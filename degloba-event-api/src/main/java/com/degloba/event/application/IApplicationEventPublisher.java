package com.degloba.event.application;

/**
 * Interfície : ApplicationEventPublisher
 */
public interface IApplicationEventPublisher<T> {
    void publish(T event);
}   
    
/*public interface IApplicationEventPublisher {
    void publish(Serializable applicationEvent);
}*/
