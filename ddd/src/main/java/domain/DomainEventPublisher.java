package domain;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
