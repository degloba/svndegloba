package com.degloba.casino.clients.infrastructure;


import com.degloba.casino.clients.Empresa;
import com.degloba.casino.clients.IEmpresaRepository;
import com.degloba.persistence.jpa.BaseAggregateRootJpaRepository;
import com.degloba.annotations.DomainRepositoryImpl;



//@DomainRepositoryImpl
public class JpaEmpresaRepository extends BaseAggregateRootJpaRepository<Empresa> implements IEmpresaRepository<Long> {

	public JpaEmpresaRepository() {
		super();
		// TODO Auto-generated constructor stub
	}

}

