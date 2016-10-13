package com.degloba.ecommerce.crm.infrastructure.persistence.rdbms.jpa.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.rdbms.jpa.EntityRepository;
import com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa.ICrmRepository;



/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class JpaCustomerRepository extends EntityRepository implements ICrmRepository{


}
