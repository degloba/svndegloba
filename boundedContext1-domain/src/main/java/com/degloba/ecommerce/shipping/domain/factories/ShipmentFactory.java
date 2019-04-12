package com.degloba.ecommerce.shipping.domain.factories;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.degloba.domain.annotations.DomainFactory;
import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
import com.degloba.ecommerce.shipping.domain.persistence.rdbms.jpa.Shipment;

/*
 * Fàbrica d'Enviament
 */
@DomainFactory
public class ShipmentFactory {

    @Inject
    private AutowireCapableBeanFactory spring;

    public Shipment createShipment(AggregateId orderId) {
    	///////Key aggregateId = KeyFactory.stringToKey( UUID.randomUUID().toString());
        Shipment shipment = new Shipment(AggregateId.generate(), orderId);
        spring.autowireBean(shipment);
        return shipment;
    }
}
