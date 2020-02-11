package com.degloba.infrastructure.ecommerce.persistence.rdbms.jpa.repositories.vendes;

import java.util.List;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.ecommerce.rdbms.jpa.vendes.IVendesRepository;
import com.degloba.domain.persistence.ecommerce.rdbms.jpa.vendes.productes.Producte;
import com.degloba.domain.persistence.ecommerce.rdbms.jpa.vendes.reserves.Reserva;
import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.jpa.EntityRepository;


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
	public Producte carregaProducte(Class<Producte> class1, AggregateId producteId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public <T extends BaseAggregateRoot> T save(T entitat) {
		return entitat;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reserva carregaReserva(Class<Reserva> clazz, AggregateId comandaId) {
		// TODO Auto-generated method stub
		return null;
	}



}
