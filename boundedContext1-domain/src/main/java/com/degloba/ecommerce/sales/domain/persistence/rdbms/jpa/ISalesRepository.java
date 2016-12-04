package com.degloba.ecommerce.sales.domain.persistence.rdbms.jpa;

import java.util.List;

import com.degloba.domain.annotations.DomainRepository;
import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.domain.persistence.rdbms.jpa.IEntityRepository;
import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.ecommerce.sales.productscatalog.domain.persistence.rdbms.jpa.Product;
import com.degloba.ecommerce.sales.reservation.domain.persistence.rdbms.jpa.Reservation;


/**
 * 
 * @author degloba
 *
 */
@DomainRepository
public interface ISalesRepository extends IEntityRepository {

	// Payment
	
	
	// Product
	
	public List<Product> findProductWhereBestBeforeExpiredIn(int days);

	public Product loadProduct(Class<Product> clazz, AggregateId productId);
	
	
	// Reservation
	
	public Reservation loadReservation(Class<Reservation> clazz, AggregateId orderId);
	
	
	// Purchase
	
	public <T extends BaseAggregateRoot> void save(T reservation);

}
