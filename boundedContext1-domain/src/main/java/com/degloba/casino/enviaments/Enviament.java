package com.degloba.casino.enviaments;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;


import com.degloba.casino.enviaments.events.EnviamentEntregatEvent;
import com.degloba.annotations.AggregateRoot;
import com.degloba.domain.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.domain.BaseAggregateRoot;


	/**
	 * @author RafaÅ‚ JamrÃ³z
	 */
	@Entity
	@AggregateRoot
	public class Enviament extends BaseAggregateRoot {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@AttributeOverrides({
			@AttributeOverride(name = "aggregateId", column = @Column(name = "orderId"))})
	    private AggregateId orderId;

	    private EnviamentStatus status;

	    
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
	        //domainEventPublisher.publish(new OrdreEnviadaEvent(orderId, getAggregateId()));	        
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

