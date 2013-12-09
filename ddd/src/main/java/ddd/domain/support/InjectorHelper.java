package ddd.domain.support;

import javax.inject.Inject;
import javax.inject.Named;

import ddd.domain.BaseAggregateRoot;
import ddd.domain.DomainEventPublisher;

@Named
public class InjectorHelper {

    @Inject
    private DomainEventPublisher eventPublisher;

    public void injectDependencies(BaseAggregateRoot aggregateRoot) {
        if (aggregateRoot != null) {
            aggregateRoot.setEventPublisher(eventPublisher);
        }
    }

	public DomainEventPublisher getEventPublisher() {
		return eventPublisher;
	}

	public void setEventPublisher(DomainEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}
    
    

}
