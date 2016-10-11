package com.degloba.ecommerce.sales.infrastructure.persistence.rdbms.jpa.repositories;

// Domain
import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.rdbms.jpa.EntityRepository;

import com.degloba.ecommerce.sales.client.domain.persistence.rdbms.jpa.IClientRepository;


/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class JpaClientRepository extends EntityRepository implements IClientRepository{



}
