package domain.support;


public interface IDomainEventPublisher<T extends IDomainEvent> {
    void publish(T event);
}

/* ddd-leaven-v2
 public interface DomainEventPublisher {

    void publish(Serializable event);
}
*/