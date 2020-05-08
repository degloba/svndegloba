package com.degloba.ecommerce.crm.infrastructure.persistence.rdbms.api.jpa.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa.ICrmRepository;
import com.degloba.persistence.rdbms.api.jpa.EntityRepository;


/**
 * Repositori + JPA : {@link Crm}
 * 
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class CrmRepository extends EntityRepository implements ICrmRepository{


}
