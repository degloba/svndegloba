/**
 * 
 */
package com.insacosa.infrastructure.repositories.jpa;

import javax.inject.Inject;

import ddd.domain.annotations.DomainRepositoryImpl;
import ddd.domain.support.InjectorHelper;
import ddd.infrastructure.repo.jpa.GenericJpaRepositoryForBaseEntity;

import com.insacosa.domain.Inmobles;
import com.insacosa.domain.repositories.InmoblesRepository;


/**
 * @author Slawek
 *
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
