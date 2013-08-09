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
public class GenericDomainServiceForBaseEntity <K,T extends BaseEntity>{

	
	private RepositoryService<Long, T> _repositori;
	
	
	public IEntityService<K,T> CreateService() {
		
		return new EntityService<K,T>(_repositori);
		
	}




}
