package com.degloba.ecommerce.sales.infrastructure.persistence.rdbms.jpa.repositories;

// Domain
import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.rdbms.jpa.EntityRepository;
// Domain (ecommerce)
import com.degloba.ecommerce.sales.reservation.domain.IReservationRepository;
import com.degloba.ecommerce.sales.reservation.domain.Reservation;

// Google App Engine
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
//public class JpaCustomerRepository extends GenericJpaRepository<Customer> implements CustomerRepository{
public class JpaReservationRepository extends EntityRepository<Reservation> implements IReservationRepository{

	@Override
	public Reservation load(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Reservation reservation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reservation load(Class<Reservation> class1, long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

}
