/**
 * 
 */
package com.insacosa.infrastructure.repositories.jpa;

import javax.inject.Inject;

import ddd.domain.annotations.DomainRepositoryImpl;
import ddd.domain.support.InjectorHelper;
import ddd.infrastructure.repo.jpa.GenericJpaRepositoryForBaseEntity;


import com.insacosa.domain.Provincies;
import com.insacosa.domain.repositories.ProvinciesRepository;

/**
 * @author Slawek
 *
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
