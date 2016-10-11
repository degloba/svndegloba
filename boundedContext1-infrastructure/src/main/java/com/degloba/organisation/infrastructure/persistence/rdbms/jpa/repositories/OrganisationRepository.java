package com.degloba.organisation.infrastructure.persistence.rdbms.jpa.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.rdbms.jpa.EntityRepository;

// Repository


// Domain (organisation)
import com.degloba.organisation.domain.persistence.rdbms.jpa.IOrganisationRepository;


/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class OrganisationRepository extends EntityRepository implements IOrganisationRepository{


}
