package com.degloba.ecommerce.shipping.cqrs.readmodel.finders;

import java.util.List;

// JPA
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

// Spring
import org.springframework.beans.factory.annotation.Qualifier;

// CQRS (ecommerce)

import com.degloba.cqrs.query.annotations.Finder;
import com.degloba.ecommerce.shipping.cqrs.readmodel.dtos.ShipmentDto;


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
