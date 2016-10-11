package com.degloba.ecommerce.sales.infrastructure.persistence.rdbms.jpa.repositories;

//Domain
import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.rdbms.jpa.EntityRepository;

// Domain (ecommerce)
import com.degloba.ecommerce.shipping.domain.IShipmentRepository;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class JpaShipmentRepository extends EntityRepository implements IShipmentRepository{



}
