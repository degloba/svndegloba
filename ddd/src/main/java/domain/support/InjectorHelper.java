package domain.support;

import javax.inject.Inject;
import javax.inject.Named;

import domain.BaseAggregateRoot;
import domain.DomainEventPublisher;

@Named
public class InjectorHelper {

    @Inject
    private DomainEventPublisher eventPublisher;

    public void injectDependencies(BaseAggregateRoot aggregateRoot) {
        if (aggregateRoot != null) {
            aggregateRoot.setEventPubslisher(eventPublisher);
        }
    }

}
