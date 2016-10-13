package com.degloba.ecommerce.shipping.cqrs.readmodel.impl;

import java.util.List;

// JPA
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

// Spring
import org.springframework.beans.factory.annotation.Qualifier;

// CQRS (ecommerce)
import com.degloba.ecommerce.shipping.cqrs.readmodel.ShipmentDto;
import com.degloba.ecommerce.shipping.cqrs.readmodel.IShipmentFinder;

import com.degloba.cqrs.query.annotations.Finder;


@Finder
public class ShipmentFinder implements IShipmentFinder {

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
