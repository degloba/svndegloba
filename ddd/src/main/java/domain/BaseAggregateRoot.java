package domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;


/**
 * @author degloba
 * 
 * @category Defineix una Entitat AggregateRoot i per tant lligada al Domini
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseAggregateRoot extends BaseEntity {

    /**
     * Sample of Domain Event usage<br>
     * Event Publisher is injected by Factory/Repo
     */
    @Transient
    protected DomainEventPublisher eventPublisher;

    /**
     * Sample technique of injecting Event Publisher into the Aggregate.<br>
     * <br>
     * Can be called only once by Factory/Repository<br>
     * Visible for package (Factory/Repository)
     */
    public void setEventPublisher(DomainEventPublisher domainEventPubslisher) {
        if (this.eventPublisher != null)
            throw new IllegalStateException("Publisher is already set! Probably You have logical error in code");
        this.eventPublisher = domainEventPubslisher;
    }

	public DomainEventPublisher getEventPublisher() {
		return eventPublisher;
	}
    
    
    
}
