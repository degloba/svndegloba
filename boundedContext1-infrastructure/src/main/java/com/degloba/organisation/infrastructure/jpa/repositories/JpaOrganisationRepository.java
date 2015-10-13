package com.degloba.organisation.infrastructure.jpa.repositories;

import com.degloba.annotations.DomainRepositoryImpl;
import com.degloba.domain.EntityRepositoryJpa;
import com.degloba.domain.GenericJpaRepository;
import com.degloba.ecommerce.crm.domain.Customer;
import com.degloba.organisation.domain.IOrganisationRepository;
import com.degloba.organisation.domain.Organization;
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
//public class JpaCustomerRepository extends GenericJpaRepository<Customer> implements CustomerRepository{
public class JpaOrganisationRepository extends EntityRepositoryJpa implements IOrganisationRepository{

	@Override
	public Organization load(Key id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Organization entity) {
		// TODO Auto-generated method stub
		
	}

}
