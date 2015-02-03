package com.degloba.domain.support;

import java.io.Serializable;

public interface DomainEventPublisher {
    void publish(Serializable event);
}

/*public interface DomainEventPublisher {
    void publish(DomainEvent event);
}*/
