package com.degloba.boundedContext.casino.domain;

import domain.seedwork.*;
import domain.support.BaseAggregateRoot;

public class EntityCasinoService<E extends BaseAggregateRoot> implements IEntityService<E> {

	private IRepository _repositori;
		
	
	public EntityCasinoService(IRepository repositori) {
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
