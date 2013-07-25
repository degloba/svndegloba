/**
 * 
 */
package com.insacosa.infrastructure.repositories.jpa;

import javax.inject.Inject;

import ddd.domain.annotations.DomainRepositoryImpl;
import ddd.domain.support.InjectorHelper;
import ddd.infrastructure.repo.jpa.GenericJpaRepositoryForBaseEntity;


import com.insacosa.domain.Ciutats;
import com.insacosa.domain.repositories.CiutatsRepository;

/**
 * @author Slawek
 *
 */
@DomainRepositoryImpl
public class JpaCiutatsRepository extends GenericJpaRepositoryForBaseEntity<Ciutats> implements CiutatsRepository{

	@Inject
	private InjectorHelper injectorHelper;
	
	@Override
	public Ciutats load(Long id) {		
		Ciutats ciutats = super.load(id);
		injectorHelper.injectDependencies(ciutats);
		return ciutats;
	}
}
