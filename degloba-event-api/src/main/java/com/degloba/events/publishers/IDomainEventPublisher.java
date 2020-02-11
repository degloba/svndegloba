package com.degloba.event.domain;


/**
 * @category Publicador d'events de domini ({@link IDomainEvent})
 * 
 * @author degloba
 */
public interface IDomainEventPublisher<T extends IDomainEvent<?>> {

	/**
	 * @category publica un event
	 * 
	 * @param event
	 */
	void publica(T event);
	
}