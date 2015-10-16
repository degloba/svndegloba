package com.degloba.ecommerce.sales.infrastructure.jpa.repositories;


import com.degloba.annotations.DomainRepositoryImpl;
import com.degloba.domain.EntityRepositoryJpa;

import com.degloba.ecommerce.sales.reservation.domain.IReservationRepository;
import com.degloba.ecommerce.sales.reservation.domain.Reservation;
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
