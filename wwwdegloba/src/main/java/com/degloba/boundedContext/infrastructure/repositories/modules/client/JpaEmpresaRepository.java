package com.degloba.boundedContext.infrastructure.repositories.modules.client;

import com.degloba.boundedContext.domain.modules.client.Empresa;
import com.degloba.boundedContext.domain.modules.client.IEmpresaRepository;

import infrastructure.repository.jpa.BaseAggregateRootJpaRepository;
import domain.annotations.DomainRepositoryImpl;
import domain.canonicalmodel.publishedlanguage.AggregateId;

@DomainRepositoryImpl
public class JpaEmpresaRepository extends BaseAggregateRootJpaRepository<Long,Empresa> 
									implements IEmpresaRepository<Long> {

	@Override
	public Empresa save(Empresa empresa) {
		// 
		this.persist(empresa);
		return null;
	}
}

