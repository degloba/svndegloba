package com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa;

import java.util.List;

import com.degloba.domain.annotations.DomainRepository;
import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.Producte;
import com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa.Reservation;
import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.jpa.IEntityRepository;


/**
 * 
 * @category Repositori de {@link Sales}
 * 
 * @author degloba
 *
 */
@DomainRepository
public interface IVendaRepository extends IEntityRepository {

	// Payment
	
	
	// Producte
	
	public List<Producte> findProductWhereBestBeforeExpiredIn(int days);

	public Producte loadProduct(Class<Producte> clazz, AggregateId productId);
	
	
	// Reserves
	
	public Reservation loadReservation(Class<Reservation> clazz, AggregateId orderId);
	
	
	// Compres
	
	public <T extends BaseAggregateRoot> T save(T reservation);

}
