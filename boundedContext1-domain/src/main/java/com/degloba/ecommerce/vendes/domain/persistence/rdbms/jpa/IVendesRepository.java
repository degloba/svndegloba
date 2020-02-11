package com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa;

import java.util.List;

import com.degloba.domain.annotations.DomainRepository;
import com.degloba.ecommerce.vendes.productes.domain.persistence.rdbms.jpa.Producte;
import com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa.Reserva;
import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.jpa.IEntityRepository;


/**
 * @category Repositori (JPA) de {@link Venda}
 * 
 * @author degloba
 *
 */
@DomainRepository
public interface IVendesRepository extends IEntityRepository {

	public List<Producte> findProductWhereBestBeforeExpiredIn(int days);
	public Producte obtenirProducteById(Class<Producte> clazz, AggregateId producteId);
	public Reserva obtenirReservaById(Class<Reserva> clazz, AggregateId comandaId);
	

}
