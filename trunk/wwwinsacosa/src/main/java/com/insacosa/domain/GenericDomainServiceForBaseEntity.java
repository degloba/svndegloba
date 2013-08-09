package com.insacosa.domain;

import ddd.domain.BaseEntity;
import ddd.domain.EntityService;
import ddd.domain.IEntityService;
import ddd.domain.RepositoryService;

/**
 * @author degloba
 *
 * @category Defineix el Servei del Domini 
 * Lligat a les entitats del domini
 * 
 * @param <T>
 */
public class GenericDomainServiceForBaseEntity <T extends BaseEntity>{

	
	private RepositoryService<Long, T> _repositori;
	
	
	public IEntityService<T> CreateService() {
		
		return new EntityService<T>(_repositori);
		
	}



}
