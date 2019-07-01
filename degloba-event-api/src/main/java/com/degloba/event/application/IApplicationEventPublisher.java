package com.degloba.event.application;

/**
 * @category Publicador d'events d'aplicació
 * 
 * 
 */
public interface IApplicationEventPublisher<T> {
	/**
	 * @category publica un event
	 * 
	 * @param event
	 */
    void publica(T event);
}   
    
/*public interface IApplicationEventPublisher {
    void publish(Serializable applicationEvent);
}*/
