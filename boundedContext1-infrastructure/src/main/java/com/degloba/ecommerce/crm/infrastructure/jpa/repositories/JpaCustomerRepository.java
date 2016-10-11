package com.degloba.ecommerce.crm.infrastructure.jpa.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.rdbms.jpa.EntityRepository;
import com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa.Customer;
import com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa.ICustomerRepository;


/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class JpaCustomerRepository extends EntityRepository<Customer> implements ICustomerRepository{

	@Override
	public Customer load(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Customer entity) {
		// TODO Auto-generated method stub
		
	}

}
