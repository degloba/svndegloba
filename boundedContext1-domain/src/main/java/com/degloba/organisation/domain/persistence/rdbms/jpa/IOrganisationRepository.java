package com.degloba.organisation.domain.persistence.rdbms.jpa;

import com.google.appengine.api.datastore.Key;

import com.degloba.domain.annotations.DomainRepository;


/**
 * @author degloba
 * 
 */
@DomainRepository
public interface IOrganisationRepository {

	public Organization load(Key id);

	public void save(Organization entity);
}
