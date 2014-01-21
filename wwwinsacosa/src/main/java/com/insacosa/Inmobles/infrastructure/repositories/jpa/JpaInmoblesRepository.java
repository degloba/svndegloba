/**
 * 
 */
package com.insacosa.Inmobles.infrastructure.repositories.jpa;

import javax.inject.Inject;

import domain.annotations.DomainRepositoryImpl;
import domain.support.InjectorHelper;
import infrastructure.repo.jpa.GenericJpaRepositoryForBaseEntity;

import com.insacosa.Inmobles.domain.Inmobles;
import com.insacosa.Inmobles.domain.repositories.InmoblesRepository;


/**
 * @author degloba
 * @category Implementació d'un Repository JPA lligat a una entitat de domini (Inmobles)
 */
@DomainRepositoryImpl
public class JpaInmoblesRepository extends GenericJpaRepositoryForBaseEntity<Inmobles> implements InmoblesRepository{

	@Inject
	private InjectorHelper injectorHelper;
	
	@Override
	public Inmobles load(Long id) {		
		Inmobles inmobles = super.load(id);
		injectorHelper.injectDependencies(inmobles);
		return inmobles;
	}
}
