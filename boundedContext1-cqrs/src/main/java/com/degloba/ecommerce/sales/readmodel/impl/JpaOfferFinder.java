package com.degloba.ecommerce.sales.readmodel.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;

import com.degloba.annotations.FinderImpl;
import com.degloba.ecommerce.sales.readmodel.offer.Offer;
import com.degloba.ecommerce.sales.readmodel.offer.OfferedProductDto;
import com.degloba.ecommerce.sales.readmodel.offer.OfferQuery;

@FinderImpl
public class JpaOfferFinder implements Offer {

	@PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactory")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<OfferedProductDto> find(OfferQuery query) {
		@SuppressWarnings("unused")
		boolean bestBeforeExpired = query.isBestBeforeExpired();
		// TODO take into consideration in query

		return (List<OfferedProductDto>) entityManager
				.createQuery(
						"SELECT NEW com.degloba.ecommerce.sales.readmodel.offer.OfferedProductDto(p.aggregateId) FROM Product p")
				.getResultList();
	}

}
