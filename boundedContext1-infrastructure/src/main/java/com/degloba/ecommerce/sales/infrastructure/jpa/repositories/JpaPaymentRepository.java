package com.degloba.ecommerce.sales.infrastructure.jpa.repositories;

import com.degloba.annotations.DomainRepositoryImpl;
import com.degloba.domain.EntityRepositoryJpa;
import com.degloba.domain.GenericJpaRepository;
import com.degloba.ecommerce.sales.payment.IPaymentRepository;
import com.degloba.ecommerce.sales.payment.Payment;
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
//public class JpaCustomerRepository extends GenericJpaRepository<Customer> implements CustomerRepository{
public class JpaPaymentRepository extends EntityRepositoryJpa implements IPaymentRepository{

	@Override
	public Payment load(Key id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Payment entity) {
		// TODO Auto-generated method stub
		
	}

}
