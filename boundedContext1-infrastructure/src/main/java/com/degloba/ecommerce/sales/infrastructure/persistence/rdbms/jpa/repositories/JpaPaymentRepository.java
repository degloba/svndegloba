package com.degloba.ecommerce.sales.infrastructure.persistence.rdbms.jpa.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.rdbms.jpa.EntityRepository;
import com.degloba.ecommerce.sales.payment.domain.persistence.rdbms.jpa.IPaymentRepository;
import com.degloba.ecommerce.sales.payment.domain.persistence.rdbms.jpa.Payment;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class JpaPaymentRepository extends EntityRepository implements IPaymentRepository{

	@Override
	public Payment load(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Payment entity) {
		// TODO Auto-generated method stub
		
	}

}
