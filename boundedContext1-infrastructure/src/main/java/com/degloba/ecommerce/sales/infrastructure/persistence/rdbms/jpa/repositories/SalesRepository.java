package com.degloba.ecommerce.sales.infrastructure.persistence.rdbms.jpa.repositories;

// Domain
import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.rdbms.jpa.EntityRepository;


import com.degloba.ecommerce.sales.domain.persistence.rdbms.jpa.ISalesRepository;


/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class SalesRepository extends EntityRepository implements ISalesRepository{



}
