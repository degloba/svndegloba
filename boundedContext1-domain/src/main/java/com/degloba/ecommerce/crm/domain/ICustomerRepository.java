package com.degloba.ecommerce.crm.domain;

import com.google.appengine.api.datastore.Key;

import com.degloba.domain.annotations.DomainRepository;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;

/**
 * @author degloba
 * 
 */
@DomainRepository
public interface ICustomerRepository {

	public Customer load(Key id);

	public void save(Customer entity);
}
