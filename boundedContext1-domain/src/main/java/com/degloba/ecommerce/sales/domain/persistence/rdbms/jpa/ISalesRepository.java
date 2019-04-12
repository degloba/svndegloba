package com.degloba.ecommerce.sales.domain.persistence.rdbms.jpa;

import java.util.List;

import com.degloba.domain.annotations.DomainRepository;

import com.degloba.ecommerce.sales.productscatalog.domain.persistence.rdbms.jpa.Product;
import com.degloba.ecommerce.sales.reservation.domain.persistence.rdbms.jpa.Reservation;
import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.jpa.IEntityRepository;


/**
 * 
 * Repositori Vendes
 * 
 * @author degloba
 *
 */
@DomainRepository
public interface ISalesRepository extends IEntityRepository {

	// Payment
	
	
	// Producte
	
	public List<Product> findProductWhereBestBeforeExpiredIn(int days);

	public Product loadProduct(Class<Product> clazz, AggregateId productId);
	
	
	// Reserves
	
	public Reservation loadReservation(Class<Reservation> clazz, AggregateId orderId);
	
	
	// Compres
	
	public <T extends BaseAggregateRoot> T save(T reservation);

}
