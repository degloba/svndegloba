package com.degloba.organisation.domain;

import com.google.appengine.api.datastore.Key;

import com.degloba.domain.annotations.DomainRepository;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;

/**
 * @author degloba
 * 
 */
@DomainRepository
public interface IOrganisationRepository {

	public Organization load(Key id);

	public void save(Organization entity);
}
