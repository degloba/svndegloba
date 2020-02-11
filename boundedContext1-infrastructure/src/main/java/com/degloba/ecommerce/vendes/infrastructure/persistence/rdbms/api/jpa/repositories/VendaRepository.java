package com.degloba.ecommerce.vendes.infrastructure.persistence.rdbms.jpa.repositories;

import java.util.List;

// Domain
import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.Producte;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.IVendesRepository;
import com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa.Reserva;
import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.jpa.EntityRepository;


/**
 * Repositori + JPA : Vendes
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
