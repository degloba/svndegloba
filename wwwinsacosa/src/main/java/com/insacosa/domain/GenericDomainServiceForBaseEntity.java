package com.insacosa.domain;

import ddd.domain.BaseEntity;
import ddd.domain.IEntityService;
import ddd.domain.RepositoryService;

public class GenericDomainServiceForBaseEntity <T extends BaseEntity>{

	
	private RepositoryService<Long, T> _repositori;
	
	
	public  IEntityService<T> CreateService() {
		return null;
		
	}
}
