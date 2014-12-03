package com.degloba.boundedContext.domain.casino;

import com.degloba.boundedContext.domain.IEntityService;

import domain.seedwork.IRepository;
import domain.support.BaseEntity;

public class CasinoClasseService implements ICasinoClasseService {

	ICasinoClasseRepository _repositori;
	
	@Override
	public IEntityService<BaseEntity> createService() {
		// 
		
		IRepository<BaseEntity> rep = this._repositori.CreateRepository();
		return new EntityCasinoService<BaseEntity>(rep);
	}

	
	public ICasinoClasseRepository get_repositori() {
		return _repositori;
	}

	
	
}
