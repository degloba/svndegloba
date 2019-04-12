package com.degloba.ecommerce.shipping.infrastructure.persistence.rdbms.jpa.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;

import com.degloba.ecommerce.shipping.domain.persistence.rdbms.jpa.IShippingRepository;
import com.degloba.persistence.rdbms.jpa.EntityRepository;


/**
 * Repositori + JPA : Enviament
 * 
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class ShippingRepository extends EntityRepository implements IShippingRepository{



}
