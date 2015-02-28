package com.degloba.domain;

import java.io.Serializable;


public interface IDomainEventPublisher<T extends IDomainEvent<?>> {
    void publish(T event);
    void publish(Serializable event);
}

/* ddd-leaven-v2
 public interface DomainEventPublisher {

    void publish(Serializable event);
}
*/