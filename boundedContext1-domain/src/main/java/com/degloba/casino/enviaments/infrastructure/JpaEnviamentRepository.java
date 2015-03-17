package com.degloba.casino.enviaments.infrastructure;


import com.degloba.casino.enviaments.Enviament;
import com.degloba.casino.enviaments.IEnviamentRepository;
import com.degloba.domain.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.persistence.datanucleus.BaseAggregateRootJpaRepository;


//@DomainRepositoryImpl
public class JpaEnviamentRepository extends BaseAggregateRootJpaRepository<Enviament> implements IEnviamentRepository<AggregateId> {

	public JpaEnviamentRepository() {
		super();
		// TODO Auto-generated constructor stub
	}  

	

  
}

