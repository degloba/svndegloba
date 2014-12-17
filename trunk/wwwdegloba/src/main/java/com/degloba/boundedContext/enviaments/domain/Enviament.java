package com.degloba.boundedContext.enviaments.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;




import com.degloba.boundedContext.enviaments.domain.events.EnviamentEntregatEvent;
import com.degloba.boundedContext.enviaments.domain.events.OrdreEnviadaEvent;

import domain.annotations.AggregateRoot;
import domain.canonicalmodel.publishedlanguage.AggregateId;
import domain.support.BaseAggregateRoot;
import domain.support.IDomainEvent;


	/**
	 * @author RafaÅ‚ JamrÃ³z
	 */
	@Entity
	@AggregateRoot
	public class Enviament extends BaseAggregateRoot {

		@AttributeOverrides({
			@AttributeOverride(name = "aggregateId", column = @Column(name = "orderId"))})
	    private AggregateId orderId;

	    private EnviamentStatus status;

	    
	    @SuppressWarnings("unused")
		public Enviament() {}

	    Enviament(AggregateId enviamentId, AggregateId orderId) {
	        //this.aggregateId = enviamentId;
	    	this.orderId = orderId;
	        this.status = EnviamentStatus.WAITING;
	    }

	    /**
	     * Enviament has been sent to the customer.
	     */
	    public void ship() {
	        if (status != EnviamentStatus.WAITING) {
	            throw new IllegalStateException("cannot ship in status " + status);
	        }
	        status = EnviamentStatus.SENT;
	        domainEventPublisher.publish(new OrdreEnviadaEvent(orderId, getAggregateId()));
	    }

	    /**
	     * Enviament has been confirmed received by the customer.
	     */
	    public void deliver() {
	        if (status != EnviamentStatus.SENT) {
	            throw new IllegalStateException("cannot deliver in status " + status);
	        }
	        status = EnviamentStatus.DELIVERED;
	        domainEventPublisher.publish(new EnviamentEntregatEvent(getAggregateId()));
	    }

	    public AggregateId getOrderId() {
	        return orderId;
	    }

	}

