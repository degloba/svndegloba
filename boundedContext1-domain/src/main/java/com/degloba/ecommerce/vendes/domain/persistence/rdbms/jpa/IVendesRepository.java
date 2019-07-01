package com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa;

import java.util.List;

import com.degloba.domain.annotations.DomainRepository;
import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.Producte;
import com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa.Reserva;
import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.jpa.IEntityRepository;


/**
 * @category Repositori de {@link Venda}
 * 
 * @author degloba
 *
 */
@DomainRepository
public interface IVendesRepository extends IEntityRepository {

	
	public List<Producte> findProductWhereBestBeforeExpiredIn(int days);
	public Producte carregaProducte(Class<Producte> clazz, AggregateId producteId);

	public Reserva carregaReserva(Class<Reserva> clazz, AggregateId comandaId);

	
	public <T extends BaseAggregateRoot> T save(T reservation);

}
