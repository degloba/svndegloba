package domain.support;

import javax.inject.Inject;
import javax.inject.Named;


@Named
public class InjectorHelper {

    @Inject
    private IDomainEventPublisher<IDomainEvent> domainEventPublisher;

    public void injectDependencies(BaseAggregateRoot aggregateRoot) {
        if (aggregateRoot != null) {
            aggregateRoot.setDomainEventPublisher(domainEventPublisher);
        }
    }

	public IDomainEventPublisher<?> getDomainEventPublisher() {
		return domainEventPublisher;
	}

	public void setDomainEventPublisher(IDomainEventPublisher<IDomainEvent> domainEventPublisher) {
		this.domainEventPublisher = domainEventPublisher;
	}
    
    

}
