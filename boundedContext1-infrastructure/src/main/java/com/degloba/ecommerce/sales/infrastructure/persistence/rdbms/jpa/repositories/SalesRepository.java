package com.degloba.ecommerce.sales.infrastructure.persistence.rdbms.jpa.repositories;

import java.util.List;

// Domain
import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.domain.persistence.rdbms.jpa.EntityRepository;
import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.ecommerce.sales.domain.persistence.rdbms.jpa.ISalesRepository;
import com.degloba.ecommerce.sales.productscatalog.domain.persistence.rdbms.jpa.Product;
import com.degloba.ecommerce.sales.reservation.domain.persistence.rdbms.jpa.Reservation;


/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class SalesRepository extends EntityRepository implements ISalesRepository{

	@Override
	public List<Product> findProductWhereBestBeforeExpiredIn(int days) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product loadProduct(Class<Product> class1, AggregateId productId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public <T extends BaseAggregateRoot> T save(T entitat) {
		return entitat;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reservation loadReservation(Class<Reservation> clazz, AggregateId orderId) {
		// TODO Auto-generated method stub
		return null;
	}



}
