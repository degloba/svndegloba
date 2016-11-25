package com.degloba.ecommerce.shipping.domain.persistence.rdbms.jpa;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.degloba.domain.annotations.AggregateRoot;
import com.degloba.domain.persistence.rdbms.jpa.AbstractEntity;
import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.ecommerce.shipping.domain.ShippingStatus;
import com.degloba.ecommerce.shipping.domain.events.ShipmentDeliveredEvent;


/**
 * @author degloba
 */
@Entity
@AggregateRoot
public class Shipment extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@AttributeOverrides({
		@AttributeOverride(name = "aggregateId", column = @Column(name = "orderId"))})  
    private AggregateId orderId;
	
    private ShippingStatus status;

    
    @SuppressWarnings("unused")
	private Shipment() {}

    public Shipment(AggregateId shipmentId, AggregateId orderId) {
        this.aggregateId = shipmentId;
    	this.orderId = orderId;
        this.status = ShippingStatus.WAITING;
    }

    /**
     * Shipment has been sent to the customer.
     */
    public void ship() {
        if (status != ShippingStatus.WAITING) {
            throw new IllegalStateException("cannot ship in status " + status);
        }
        status = ShippingStatus.SENT;
  ////////eventPublisher.publish(new OrderShippedEvent(orderId, getAggregateId()));
    }

    /**
     * Shipment has been confirmed received by the customer.
     */
    public void deliver() {
        if (status != ShippingStatus.SENT) {
            throw new IllegalStateException("cannot deliver in status " + status);
        }
        status = ShippingStatus.DELIVERED;
        ////////eventPublisher.publish(new ShipmentDeliveredEvent(getAggregateId()));
    }

    public AggregateId getOrderId() {    	
    	return orderId;
    }



}
