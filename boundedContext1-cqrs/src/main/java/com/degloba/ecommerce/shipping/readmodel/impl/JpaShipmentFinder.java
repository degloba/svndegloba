package com.degloba.ecommerce.shipping.readmodel.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;

import query.annotations.Finder;

import com.degloba.ecommerce.shipping.readmodel.ShipmentDto;
import com.degloba.ecommerce.shipping.readmodel.ShipmentFinder;

@Finder
public class JpaShipmentFinder implements ShipmentFinder {

    @PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactoryDatastore")
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
	@Override
    public List<ShipmentDto> findShipment() {
        String jpql = "select new com.degloba.ecommerce.shipping.readmodel.ShipmentDto(s.id, s.orderId, s.status) from com.degloba.ecommerce.shipping.domain.Shipment s";
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }
}
