package com.degloba.ecommerce.crm.infrastructure.jpa.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.rdbms.jpa.JpaEntityRepository;
import com.degloba.ecommerce.crm.domain.Customer;
import com.degloba.ecommerce.crm.domain.ICustomerRepository;
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class JpaCustomerRepository extends JpaEntityRepository<Customer> implements ICustomerRepository{

	@Override
	public Customer load(Key id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Customer entity) {
		// TODO Auto-generated method stub
		
	}

}
