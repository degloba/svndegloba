/**
 * 
 */
package com.insacosa.Inmobles.infrastructure.repositories.jpa;

import javax.inject.Inject;

import domain.annotations.DomainRepositoryImpl;
import domain.support.InjectorHelper;
import infrastructure.repo.jpa.GenericJpaRepositoryForBaseEntity;


import com.insacosa.Inmobles.domain.Ciutats;
import com.insacosa.Inmobles.domain.repositories.CiutatsRepository;

/**
 * @author degloba
 * @category Implementació d'un Repository JPA lligat a una entitat de domini (Ciutats)
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
