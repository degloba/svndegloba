/**
 * 
 */
package com.insacosa.infrastructure.repositories.jpa;

import javax.inject.Inject;

import ddd.domain.annotations.DomainRepositoryImpl;
import ddd.domain.support.InjectorHelper;
import ddd.infrastructure.repo.jpa.GenericJpaRepositoryForBaseEntity;


import com.insacosa.domain.Tipus;
import com.insacosa.domain.repositories.TipusRepository;

/**
 * @author Slawek
 * @category Implementació d'un Repository JPA lligat a una entitat de domini (Tipus)
 */
@DomainRepositoryImpl
public class JpaTipusRepository extends GenericJpaRepositoryForBaseEntity<Tipus> implements TipusRepository{

	@Inject
	private InjectorHelper injectorHelper;
	
	@Override
	public Tipus load(Long id) {		
		Tipus tipus = super.load(id);
		injectorHelper.injectDependencies(tipus);
		return tipus;
	}
}
