package com.insacosa.domain.repositories;

import javax.inject.Inject;
import javax.persistence.criteria.Predicate;


import ddd.domain.BaseEntity;


public abstract class GenericApplicationService<K,E extends BaseEntity> implements IEntityService<E> {

	protected Class<E> entityClass;  
	
	@Inject
	private PersistenceService<K,E> _repositori;


	@Override
	public void Delete(Predicate predicate) {
		_repositori.Delete(predicate);
		
	}
	
	@Override
	public void Delete(E entitat) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void Add(E entitat) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void Update(E entitat) {
		// TODO Auto-generated method stub
		
	}




	

	
	
}
