package com.degloba.ecommerce.enviaments.cqrs.queries.finders;

import java.util.List;

// JPA
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

// Spring
import org.springframework.beans.factory.annotation.Qualifier;

// CQRS (ecommerce)

import com.degloba.cqrs.query.annotations.Finder;
import com.degloba.ecommerce.enviaments.facade.dtos.EnviamentDto;


/**
 * @author degloba
 *
 * @category {@link Finder} de {@link Enviament}
 */
@Finder
public class EnviamentFinder implements IEnviamentFinder {

    @PersistenceContext(name="transactions-optional")
    @Qualifier(value="entityManagerFactoryDatastore")
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
	@Override
    public List<EnviamentDto> buscaEnviaments() {
        String jpql = "select new com.degloba.ecommerce.enviaments.cqrs.readmodel.dtos.EnviamentDto(s.id, s.comandaId, s.status) from com.degloba.ecommerce.shipping.domain.Shipment s";
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }
}
