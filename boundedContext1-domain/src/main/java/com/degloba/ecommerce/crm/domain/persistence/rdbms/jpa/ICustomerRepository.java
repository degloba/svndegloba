package com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa;


import com.degloba.domain.annotations.DomainRepository;


/**
 * @author degloba
 * 
 */
@DomainRepository
public interface ICustomerRepository {

	public Customer load(long id);

	public void save(Customer entity);
}
