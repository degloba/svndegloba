package com.degloba.event.domain;

import java.io.Serializable;

/**
 * Interfície : DomainEventPublisher de tipus {@link IDomainEvent}
 */
public interface IDomainEventPublisher<T extends IDomainEvent<?>> {
	
    void publish(T event);
    void publish(Serializable event);
}