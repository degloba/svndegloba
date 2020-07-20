package com.degloba.ecommerce.enviaments.cqrs.queries.finders;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;

import com.degloba.cqrs.queries.annotations.FinderAnnotation;
import com.degloba.ecommerce.enviaments.facade.dtos.EnviamentDto;


/**
 * @author degloba
 *
 * @category es {@link FinderAnnotation} de {@link Enviament}
 * 
 * @implNote JPA
 * 
 */
@FinderAnnotation
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
