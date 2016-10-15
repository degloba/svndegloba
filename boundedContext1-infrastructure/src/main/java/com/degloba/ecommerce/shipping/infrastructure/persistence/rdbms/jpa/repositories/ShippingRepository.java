package com.degloba.ecommerce.shipping.infrastructure.persistence.rdbms.jpa.repositories;

// Domain
import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.rdbms.jpa.EntityRepository;



import com.degloba.ecommerce.shipping.domain.persistence.rdbms.jpa.IShippingRepository;


/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class ShippingRepository extends EntityRepository implements IShippingRepository{



}
