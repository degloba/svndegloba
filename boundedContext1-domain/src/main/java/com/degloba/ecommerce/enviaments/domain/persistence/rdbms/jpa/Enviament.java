package com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.degloba.domain.annotations.AggregateRoot;
import com.degloba.ecommerce.enviaments.domain.EstatEnviament;
import com.degloba.ecommerce.enviaments.domain.events.EnviamentLliuratEvent;
import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
import com.degloba.persistence.rdbms.jpa.BaseEntity;


/**
 * Entitat : Enviament
 * 
 * @author degloba
 */
@Entity
@AggregateRoot
public class Enviament extends BaseAggregateRoot {

	private static final long serialVersionUID = 1L;
	
	
	@AttributeOverrides({
		@AttributeOverride(name = "aggregateId", column = @Column(name = "orderId"))})  
    private AggregateId orderId;
	
    private EstatEnviament status;

    
    private Enviament() {}

    public Enviament(AggregateId shipmentId, AggregateId orderId) {
        this.aggregateId = shipmentId;
    	this.orderId = orderId;
        this.status = EstatEnviament.WAITING;
    }

    /**
     * Shipment has been sent to the customer.
     */
    public void ship() {
        if (status != EstatEnviament.WAITING) {
            throw new IllegalStateException("cannot ship in status " + status);
        }
        status = EstatEnviament.SENT;
  ////////eventPublisher.publish(new OrderShippedEvent(orderId, getAggregateId()));
    }

    /**
     * Shipment has been confirmed received by the customer.
     */
    public void deliver() {
        if (status != EstatEnviament.SENT) {
            throw new IllegalStateException("cannot deliver in status " + status);
        }
        status = EstatEnviament.DELIVERED;
        ////////eventPublisher.publish(new ShipmentDeliveredEvent(getAggregateId()));
    }

    public AggregateId getOrderId() {    	
    	return orderId;
    }


}
