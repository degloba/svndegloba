package com.degloba.event.domain;


/**
 * @category Publicador d'events de domini ({@link IDomainEvent})
 * 
 * @author degloba
 */
public interface IDomainEventPublisher<T extends IDomainEvent<?>> {

	void publica(T event);
	
}