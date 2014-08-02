package domain.support;

import javax.inject.Inject;
import javax.inject.Named;


@Named
public class InjectorHelper {

    @Inject
    private IDomainEventPublisher<?> domainEventPublisher;

    public void injectDependencies(BaseAggregateRoot aggregateRoot) {
        if (aggregateRoot != null) {
            aggregateRoot.setDomainEventPublisher(domainEventPublisher);
        }
    }

	public IDomainEventPublisher<?> getDomainEventPublisher() {
		return domainEventPublisher;
	}

	public void setDomainEventPublisher(IDomainEventPublisher<?> domainEventPublisher) {
		this.domainEventPublisher = domainEventPublisher;
	}
    
    

}
