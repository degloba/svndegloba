package com.degloba.boundedContext.clients.domain;


import com.degloba.boundedContext.casino.domain.IEntityService;
import domain.support.BaseAggregateRoot;


public class EmpresaService implements IEmpresaService<Long> {

	IEmpresaRepository<Long> _repositori;
	

	public EmpresaService() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EmpresaService(IEmpresaRepository<Long> _repositori) {
		super();
		this._repositori = _repositori;
	}
	
	@Override
	public IEntityService<BaseAggregateRoot> createService() {
		return null;
		// 
		
		/*IRepository<BaseAggregateRoot> rep = this._repositori.CreateRepository();
		return new EntityCasinoService<BaseAggregateRoot>(rep);*/
	}

	
	public IEmpresaRepository<?> get_repositori() {
		return _repositori;
	}
	
}