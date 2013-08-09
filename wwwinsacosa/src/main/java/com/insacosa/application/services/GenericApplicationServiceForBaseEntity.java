package com.insacosa.application.services;

import javax.inject.Inject;

import com.insacosa.domain.GenericDomainServiceForBaseEntity;
import com.insacosa.domain.InvoicingService;


import ddd.domain.BaseEntity;
import ddd.domain.IEntityService;
import ddd.domain.annotations.DomainService;


/**
 * @author degloba
 * Defineix el Servei d'Aplicació lligat al Domini (BaseEntity)
 * @param <E>
 */
public abstract class GenericApplicationServiceForBaseEntity<E extends BaseEntity> extends GenericApplicationService<E> {

	protected Class<E> entityClass;  
	
	GenericDomainServiceForBaseEntity<E> ds;
	
	protected  void SaveBaseEntity(E value) {
		
	}
	
	protected  void RemoveBaseEntity(E value) {
		
	}
	



		
}
