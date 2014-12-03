package com.degloba.boundedContext.domain.casino;

import com.degloba.boundedContext.domain.IEntityService;

import domain.seedwork.*;
import domain.support.BaseEntity;

public class EntityCasinoService<E extends BaseEntity> implements IEntityService<E> {

	private IRepository<E> _repositori;
		
	
	public EntityCasinoService(IRepository<E> repositori) {
		super();
		this._repositori = repositori;
	}


	@Override
	public void Add(E value) {
		// 
		this._repositori.add(value);
	}


	@Override
	public void remove(E value) {
		// 
		this._repositori.remove(value);
		
	}


	@Override
	public void removeById(E value) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(E value) {
		// 
		this._repositori.update(value);
	}


	@Override
	public void merge(E persisted, E current) {
		// TODO Auto-generated method stub
				
	}


	
	
	
	

}
