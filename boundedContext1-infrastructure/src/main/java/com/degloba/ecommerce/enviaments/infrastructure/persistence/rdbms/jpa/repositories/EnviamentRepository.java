package com.degloba.ecommerce.enviaments.infrastructure.persistence.rdbms.jpa.repositories;

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
public class EnviamentRepository extends EntityRepository implements IEnviamentRepository{



}
