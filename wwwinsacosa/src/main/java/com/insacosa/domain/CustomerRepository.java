/**
 * 
 */
package com.insacosa.domain;

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
