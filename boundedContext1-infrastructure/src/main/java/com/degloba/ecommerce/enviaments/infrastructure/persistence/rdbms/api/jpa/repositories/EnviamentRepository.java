package com.degloba.ecommerce.enviaments.infrastructure.persistence.rdbms.api.jpa.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.IEnviamentsRepository;
import com.degloba.persistence.rdbms.jpa.EntityRepository;


/**
 * @category Repositori (JPA) : {@link Enviament}
 * 
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class EnviamentRepository extends EntityRepository implements IEnviamentsRepository{



}
