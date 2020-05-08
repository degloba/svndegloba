package com.degloba.ecommerce.vendes.infrastructure.persistence.rdbms.api.jpa.repositories;

import java.util.List;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.IVendesRepository;
import com.degloba.ecommerce.vendes.productes.domain.persistence.rdbms.jpa.Producte;
import com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa.Reserva;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;
import com.degloba.persistence.rdbms.api.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.api.jpa.EntityRepository;


/**
 * @category Repositori (JPA) : Vendes
 * 
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class VendaRepository extends EntityRepository implements IVendesRepository{

	@Override
	public List<Producte> findProductWhereBestBeforeExpiredIn(int days) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public <T extends BaseAggregateRoot> T save(T entitat) {
		return entitat;
		// TODO Auto-generated method stub
		
	}


	@Override
	public Producte obtenirProducteById(Class<Producte> clazz, AggregateId producteId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Reserva obtenirReservaById(Class<Reserva> clazz, AggregateId comandaId) {
		// TODO Auto-generated method stub
		return null;
	}

}
