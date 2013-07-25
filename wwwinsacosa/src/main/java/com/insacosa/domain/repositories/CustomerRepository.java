/**
 * 
 */
package com.insacosa.domain.repositories;

import com.insacosa.domain.Customer;

import ddd.domain.annotations.DomainRepository;

/**
 * @author Slawek
 * 
 */
@DomainRepository
public interface CustomerRepository {

	public Customer load(Long id);

	public Customer save(Customer entity);
}
