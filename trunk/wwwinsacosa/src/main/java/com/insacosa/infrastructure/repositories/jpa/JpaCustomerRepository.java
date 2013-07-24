/**
 * 
 */
package com.insacosa.infrastructure.repositories.jpa;

import javax.inject.Inject;

import ddd.domain.annotations.DomainRepositoryImpl;
import ddd.domain.support.InjectorHelper;
import ddd.infrastructure.repo.jpa.GenericJpaRepositoryForBaseEntity;

import com.insacosa.domain.Customer;
import com.insacosa.domain.CustomerRepository;

/**
 * @author Slawek
 *
 */
@DomainRepositoryImpl
public class JpaCustomerRepository extends GenericJpaRepositoryForBaseEntity<Customer> implements CustomerRepository{

	@Inject
	private InjectorHelper injectorHelper;
	
	@Override
	public Customer load(Long id) {		
		Customer customer = super.load(id);
		injectorHelper.injectDependencies(customer);
		return customer;
	}
}
