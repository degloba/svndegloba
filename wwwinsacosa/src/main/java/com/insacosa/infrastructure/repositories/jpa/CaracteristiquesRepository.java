/**
 * 
 */
package com.insacosa.infrastructure.repositories.jpa;

import javax.inject.Inject;

import ddd.domain.annotations.DomainRepositoryImpl;
import ddd.domain.support.InjectorHelper;
import ddd.infrastructure.repo.jpa.GenericJpaRepositoryForBaseEntity;


import com.insacosa.domain.Solicituds;
import com.insacosa.domain.repositories.SolicitudsRepository;

/**
 * @author Slawek
 *
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
