package domain.support;

import javax.inject.Inject;
import javax.inject.Named;


@Named
public class InjectorHelper {

    @Inject
    private DomainEventPublisher domainEventPublisher;

    public void injectDependencies(BaseAggregateRoot aggregateRoot) {
        if (aggregateRoot != null) {
            aggregateRoot.setDomainEventPublisher(domainEventPublisher);
        }
    }

	public DomainEventPublisher getDomainEventPublisher() {
		return domainEventPublisher;
	}

	public void setDomainEventPublisher(DomainEventPublisher domainEventPublisher) {
		this.domainEventPublisher = domainEventPublisher;
	}
    
    

}
