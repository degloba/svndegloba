package com.degloba.ecommerce.sales.infrastructure.jpa.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.rdbms.jpa.EntityRepository;
import com.degloba.ecommerce.sales.payment.domain.persistence.rdbms.jpa.IPaymentRepository;
import com.degloba.ecommerce.sales.payment.domain.persistence.rdbms.jpa.Payment;
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
//public class JpaCustomerRepository extends GenericJpaRepository<Customer> implements CustomerRepository{
public class JpaPaymentRepository extends EntityRepository<Payment> implements IPaymentRepository{

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
