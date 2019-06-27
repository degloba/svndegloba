package com.degloba.ecommerce.enviaments.domain.factories;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.degloba.domain.annotations.DomainFactory;
import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.Enviament;
import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseEntity;

/*
 * Fàbrica d'Enviament
 */
@DomainFactory
public class ShipmentFactory {

    @Inject
    private AutowireCapableBeanFactory spring;

    public Enviament createShipment(AggregateId orderId) {
    	///////Key aggregateId = KeyFactory.stringToKey( UUID.randomUUID().toString());
        Enviament enviament = new Enviament(AggregateId.generate(), orderId);
        spring.autowireBean(enviament);
        return enviament;
    }
}
