package com.degloba.ecommerce.shipping.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

import com.degloba.domain.jpa.BaseAggregateRoot;
import com.degloba.ecommerce.shipping.domain.events.OrderShippedEvent;
import com.degloba.ecommerce.shipping.domain.events.ShipmentDeliveredEvent;
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 */
@Entity
//@AggregateRoot
public class Shipment extends BaseAggregateRoot {

/*	@AttributeOverrides({
		@AttributeOverride(name = "aggregateId", column = @Column(name = "orderId"))})
    private Key orderId;*/

    private ShippingStatus status;

    
    @SuppressWarnings("unused")
	private Shipment() {}

    Shipment(Key shipmentId, Key orderId) {
        ////////this.aggregateId = shipmentId;
    	////////this.orderId = orderId;
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
        eventPublisher.publish(new ShipmentDeliveredEvent(getAggregateId()));
    }

    public Key getOrderId() {
    	return null;
  ////////return orderId;
    }

}
