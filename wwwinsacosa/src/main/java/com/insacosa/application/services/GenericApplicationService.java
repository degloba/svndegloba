package com.insacosa.application.services;

import javax.inject.Inject;
import javax.persistence.criteria.Predicate;

import ddd.domain.Entitat;



/**
 * @author degloba
 * 
 * Defineix el Servei d'Aplicació NO lligat al Domini (BaseEntity)
 * @param <K>
 * @param <E>
 */
public class GenericApplicationService<E extends Entitat> {

	protected Class<E> entityClass;  

		
	
}
