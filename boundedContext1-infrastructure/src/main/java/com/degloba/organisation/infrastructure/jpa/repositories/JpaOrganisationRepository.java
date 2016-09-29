package com.degloba.organisation.infrastructure.jpa.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.jpa.JpaEntityRepository;

// Repository


// Domain (organisation)
import com.degloba.organisation.domain.IOrganisationRepository;
import com.degloba.organisation.domain.Organization;

// Google App Engine
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class JpaOrganisationRepository extends JpaEntityRepository<Organization> implements IOrganisationRepository{

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
