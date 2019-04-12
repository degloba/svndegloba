package com.degloba.organisation.infrastructure.persistence.rdbms.jpa.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;


import com.degloba.organisation.domain.persistence.rdbms.jpa.IOrganisationRepository;
import com.degloba.persistence.rdbms.jpa.EntityRepository;


/**
 * Repositori + JPA : Organitzacio
 * 
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class OrganisationRepository extends EntityRepository implements IOrganisationRepository{


}
