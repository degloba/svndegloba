package com.insacosa.Inmobles.domain;

import domain.BaseEntity;
import domain.EntityService;
import domain.IEntityService;
import domain.RepositoryService;

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
