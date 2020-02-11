package com.degloba.events.publishers;

/**
 * @category Publicador d'events d'aplicació
 * 
 * @author degloba
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
