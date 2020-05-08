package com.degloba.ecommerce.vendes.infrastructure.persistence.rdbms.api.jpa.repositories;

import java.util.List;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.IDescompteRepository;

import com.degloba.persistence.rdbms.api.jpa.AggregateId;
import com.degloba.persistence.rdbms.api.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.api.jpa.EntityRepository;


/**
 * @category Repositori (JPA) : Descompte
 * 
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class DescompteRepository extends EntityRepository implements IDescompteRepository{

		
	@Override
	public <T extends BaseAggregateRoot> T save(T entitat) {
		return entitat;
		// TODO Auto-generated method stub
		
	}


}
