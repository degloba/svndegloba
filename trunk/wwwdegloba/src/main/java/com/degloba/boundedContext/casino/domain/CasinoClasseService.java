package com.degloba.boundedContext.casino.domain;


import domain.seedwork.IRepository;
import domain.support.BaseAggregateRoot;
import domain.support.BaseEntity;

public class CasinoClasseService implements ICasinoClasseService {

	ICasinoClasseRepository _repositori;
	

	public CasinoClasseService() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CasinoClasseService(ICasinoClasseRepository _repositori) {
		super();
		this._repositori = _repositori;
	}
	
	@Override
	public IEntityService<BaseAggregateRoot> createService() {
		// 
		
		IRepository<BaseAggregateRoot> rep = this._repositori.CreateRepository();
		return new EntityCasinoService<BaseAggregateRoot>(rep);
	}

	
	public ICasinoClasseRepository get_repositori() {
		return _repositori;
	}
	
}
