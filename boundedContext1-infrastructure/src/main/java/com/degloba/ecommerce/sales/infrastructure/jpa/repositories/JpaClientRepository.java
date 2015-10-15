package com.degloba.ecommerce.sales.infrastructure.jpa.repositories;

import com.degloba.annotations.DomainRepositoryImpl;
import com.degloba.domain.EntityRepositoryJpa;
import com.degloba.domain.GenericJpaRepository;
import com.degloba.ecommerce.crm.domain.Customer;
import com.degloba.ecommerce.crm.domain.ICustomerRepository;
import com.degloba.organisation.domain.client.Client;
import com.degloba.organisation.domain.client.IClientRepository;
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
//public class JpaCustomerRepository extends GenericJpaRepository<Customer> implements CustomerRepository{
public class JpaClientRepository extends EntityRepositoryJpa<Client> implements IClientRepository{

	@Override
	public Client load(Key id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Client entity) {
		// TODO Auto-generated method stub
		
	}

}
