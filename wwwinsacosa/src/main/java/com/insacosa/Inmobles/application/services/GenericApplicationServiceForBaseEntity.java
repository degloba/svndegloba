package com.insacosa.Inmobles.application.services;

import com.insacosa.Inmobles.domain.GenericDomainServiceForBaseEntity;

import domain.BaseEntity;


/**
 * @author degloba
 * Defineix el Servei d'Aplicació lligat al Domini (BaseEntity)
 * @param <E>
 */
public abstract class GenericApplicationServiceForBaseEntity<K,E extends BaseEntity> extends GenericApplicationService<E> {

	protected Class<E> entityClass;  
	
	public GenericDomainServiceForBaseEntity<K,E> ds;
	
	protected  void SaveBaseEntity(E value) {
		
	}
	
	protected  void RemoveBaseEntity(E value) {
		
	}
	



		
}
