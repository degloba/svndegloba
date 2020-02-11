package com.degloba.infrastructure.ecommerce.persistence.rdbms.jpa.repositories.crm;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.ecommerce.rdbms.jpa.crm.ICrmRepository;
import com.degloba.persistence.rdbms.jpa.EntityRepository;


/**
 * Repositori + JPA : {@link Crm}
 * 
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class CrmRepository extends EntityRepository implements ICrmRepository{


}
