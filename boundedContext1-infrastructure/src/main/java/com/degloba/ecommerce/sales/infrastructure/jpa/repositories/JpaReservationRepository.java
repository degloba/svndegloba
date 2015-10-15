package com.degloba.ecommerce.sales.infrastructure.jpa.repositories;

import java.util.List;

import com.degloba.annotations.DomainRepositoryImpl;
import com.degloba.domain.EntityRepositoryJpa;
import com.degloba.domain.GenericJpaRepository;
import com.degloba.ecommerce.sales.productscatalog.Product;
import com.degloba.ecommerce.sales.reservation.IReservationRepository;
import com.degloba.ecommerce.sales.reservation.Reservation;
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
//public class JpaCustomerRepository extends GenericJpaRepository<Customer> implements CustomerRepository{
public class JpaReservationRepository extends EntityRepositoryJpa<Reservation> implements IReservationRepository{

	@Override
	public Reservation load(Key id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Reservation reservation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reservation load(Class<Reservation> class1, Key orderId) {
		// TODO Auto-generated method stub
		return null;
	}

}
