package com.degloba.ecommerce.shipping.infrastructure.persistence.rdbms.jpa.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.IEnviamentRepository;
import com.degloba.persistence.rdbms.jpa.EntityRepository;


/**
 * Repositori + JPA : Enviament
 * 
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class ShippingRepository extends EntityRepository implements IEnviamentRepository{



}
