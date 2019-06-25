package com.degloba.ecommerce.enviaments.cqrs.readmodel.finders;

import java.util.List;

// JPA
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

// Spring
import org.springframework.beans.factory.annotation.Qualifier;

// CQRS (ecommerce)

import com.degloba.cqrs.query.annotations.Finder;
import com.degloba.ecommerce.enviaments.cqrs.readmodel.dtos.EnviamentDto;

/**
 * 
 * @author degloba
 *
 * @category {@link Finder} de {@link Enviament}
 */
@Finder
public class EnviamentFinder implements IEnviamentFinder {

    @PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactoryDatastore")
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
	@Override
    public List<EnviamentDto> findShipment() {
        String jpql = "select new com.degloba.ecommerce.enviaments.readmodel.EnviamentDto(s.id, s.orderId, s.status) from com.degloba.ecommerce.shipping.domain.Shipment s";
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }
}