package com.degloba.event.application;

/**
 * @category Interfície : ApplicationEventPublisher
 */
public interface IApplicationEventPublisher<T> {
    void publish(T event);
}   
    
/*public interface IApplicationEventPublisher {
    void publish(Serializable applicationEvent);
}*/
