package com.degloba.boundedContext.clients.infrastructure;


import com.degloba.boundedContext.clients.domain.Empresa;
import com.degloba.boundedContext.clients.domain.IEmpresaRepository;

import infrastructure.repository.jpa.BaseAggregateRootJpaRepository;
import domain.annotations.DomainRepositoryImpl;
import domain.canonicalmodel.publishedlanguage.AggregateId;

@DomainRepositoryImpl
public class JpaEmpresaRepository extends BaseAggregateRootJpaRepository<AggregateId,Empresa> 
									implements IEmpresaRepository<AggregateId> {

	public JpaEmpresaRepository() {
		super();
		// TODO Auto-generated constructor stub
	}

}

