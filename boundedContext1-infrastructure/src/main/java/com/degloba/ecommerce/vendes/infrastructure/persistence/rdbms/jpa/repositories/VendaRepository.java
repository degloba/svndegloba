package com.degloba.ecommerce.vendes.infrastructure.persistence.rdbms.jpa.repositories;

import java.util.List;

// Domain
import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.Producte;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.IVendaRepository;
import com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa.Reservation;
import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.jpa.EntityRepository;


/**
 * Repositori + JPA : Vendes
 * 
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class VendaRepository extends EntityRepository implements IVendaRepository{

	@Override
	public List<Producte> findProductWhereBestBeforeExpiredIn(int days) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producte loadProduct(Class<Producte> class1, AggregateId productId) {
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
