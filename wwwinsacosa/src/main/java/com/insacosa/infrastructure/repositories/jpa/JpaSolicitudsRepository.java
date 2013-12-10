/**
 * 
 */
package com.insacosa.infrastructure.repositories.jpa;

import javax.inject.Inject;

import domain.annotations.DomainRepositoryImpl;
import domain.support.InjectorHelper;
import infrastructure.repo.jpa.GenericJpaRepositoryForBaseEntity;


import com.insacosa.domain.Solicituds;
import com.insacosa.domain.repositories.SolicitudsRepository;

/**
 * @author degloba
 * @category Implementació d'un Repository JPA lligat a una entitat de domini (Solicituds)
 */
@DomainRepositoryImpl
public class JpaSolicitudsRepository extends GenericJpaRepositoryForBaseEntity<Solicituds> implements SolicitudsRepository{

	@Inject
	private InjectorHelper injectorHelper;
	
	@Override
	public Solicituds load(Long id) {		
		Solicituds customer = super.load(id);
		injectorHelper.injectDependencies(customer);
		return customer;
	}
}
