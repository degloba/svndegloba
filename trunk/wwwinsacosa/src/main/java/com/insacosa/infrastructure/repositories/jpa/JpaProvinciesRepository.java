/**
 * 
 */
package com.insacosa.infrastructure.repositories.jpa;

import javax.inject.Inject;

import domain.annotations.DomainRepositoryImpl;
import domain.support.InjectorHelper;
import infrastructure.repo.jpa.GenericJpaRepositoryForBaseEntity;


import com.insacosa.domain.Provincies;
import com.insacosa.domain.repositories.ProvinciesRepository;

/**
 * @author degloba
 * @category Implementació d'un Repository JPA lligat a una entitat de domini (Provincies)
 */
@DomainRepositoryImpl
public class JpaProvinciesRepository extends GenericJpaRepositoryForBaseEntity<Provincies> implements ProvinciesRepository{

	@Inject
	private InjectorHelper injectorHelper;
	
	@Override
	public Provincies load(Long id) {		
		Provincies provincies = super.load(id);
		injectorHelper.injectDependencies(provincies);
		return provincies;
	}
}
