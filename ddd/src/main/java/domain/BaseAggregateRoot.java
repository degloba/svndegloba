package domain;

// CDI Java EE 6
import javax.inject.Inject;

// JPA
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

// SPRING
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import domain.canonicalmodel.publishedlanguage.AggregateId;
import domain.sharedkernel.exceptions.DomainOperationException;


/**
 * @author degloba
 * 
 * @category Defineix la Entitat Base del Domini AggregateRoot i per tant lligada al Domini
 * Lligat al Domini.Totes les entitats del Domini hereden d'ella
 * 
 */

	@Component
	@Scope("prototype")//created in domain factories, not in spring container, therefore we don't want eager creation
	@MappedSuperclass
	public abstract class BaseAggregateRoot {
		public static enum AggregateStatus {
			ACTIVE, ARCHIVE
		}
	
		/*@EmbeddedId
		@AttributeOverrides({
			  @AttributeOverride(name = "aggregateId", column = @Column(name = "aggregateId", nullable = false))})
		private AggregateId aggregateId;
		*/
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		
		@Version
		private Long version;

		@Enumerated(EnumType.ORDINAL)
		private AggregateStatus aggregateStatus = AggregateStatus.ACTIVE;
		
		@Transient
		@Inject
		protected DomainEventPublisher domainEventPublisher;
		
		public void markAsRemoved() {
			aggregateStatus = AggregateStatus.ARCHIVE;
		}
		

/*		public AggregateId getAggregateId() {
			return aggregateId;
		}

		public boolean isRemoved() {
			return aggregateStatus == AggregateStatus.ARCHIVE;
		}
		
		protected void domainError(String message) {
			throw new DomainOperationException(aggregateId, message);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (obj instanceof BaseAggregateRoot) {
				BaseAggregateRoot other = (BaseAggregateRoot) obj;
				if (other.aggregateId == null)
					return false;
				return other.aggregateId.equals(aggregateId);
			}
			
			return false;
		}
		
		@Override
		public int hashCode() {	
			return aggregateId.hashCode();
		}	*/	
		

	
    /**
     * Sample of Domain Event usage<br>
     * Event Publisher is injected by Factory/Repo 
     
    @Transient
    protected DomainEventPublisher eventPublisher; */

    /**
     * Sample technique of injecting Event Publisher into the Aggregate.<br>
     * <br>
     * Can be called only once by Factory/Repository<br>
     * Visible for package (Factory/Repository)
     */
   public void setDomainEventPublisher(DomainEventPublisher domainEventPubslisher) {
        if (this.domainEventPublisher != null)
            throw new IllegalStateException("Publisher is already set! Probably You have logical error in code");
        this.domainEventPublisher = domainEventPubslisher;
    }

	public DomainEventPublisher getDomainEventPublisher() {
		return domainEventPublisher;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

  

    
}
