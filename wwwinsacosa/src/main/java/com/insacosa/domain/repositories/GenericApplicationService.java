package com.insacosa.domain.repositories;

import javax.inject.Inject;
import javax.persistence.criteria.Predicate;


import ddd.domain.BaseEntity;


public class GenericApplicationService<K,E> {

	protected Class<E> entityClass;  
	
	@Inject
	private RepositoryService<K,E> _repositori;

	
	public void Delete(E entitat) {
		// TODO Auto-generated method stub
		
	}
	
	public void Add(E entitat) {
		// TODO Auto-generated method stub
		
	}


	public void Update(E entitat) {
		// TODO Auto-generated method stub
		
	}




	

	
	
}
