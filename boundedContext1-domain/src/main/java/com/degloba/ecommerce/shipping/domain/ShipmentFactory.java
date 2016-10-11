package com.degloba.ecommerce.shipping.domain;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.degloba.domain.annotations.DomainFactory;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@DomainFactory
public class ShipmentFactory {

    @Inject
    private AutowireCapableBeanFactory spring;

    public Shipment createShipment(long orderId) {
    	Key aggregateId = KeyFactory.stringToKey( UUID.randomUUID().toString());
        Shipment shipment = new Shipment(aggregateId, orderId);
        spring.autowireBean(shipment);
        return shipment;
    }
}
