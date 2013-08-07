package com.insacosa.domain.repositories;

import javax.inject.Inject;
import javax.persistence.criteria.Predicate;


import ddd.domain.BaseEntity;


public abstract class GenericApplicationServiceForBaseEntity<K,E extends BaseEntity> extends GenericApplicationService<K,E> {

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
