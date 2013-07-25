/**
 * 
 */
package com.insacosa.infrastructure.repositories.jpa;

import javax.inject.Inject;

import ddd.domain.annotations.DomainRepositoryImpl;
import ddd.domain.support.InjectorHelper;
import ddd.infrastructure.repo.jpa.GenericJpaRepositoryForBaseEntity;


import com.insacosa.domain.Caracteristiques;
import com.insacosa.domain.repositories.CaracteristiquesRepository;

/**
 * @author Slawek
 *
 */
@DomainRepositoryImpl
public class JpaCaracteristiquesRepository extends GenericJpaRepositoryForBaseEntity<Caracteristiques> implements CaracteristiquesRepository{

	@Inject
	private InjectorHelper injectorHelper;
	
	@Override
	public Caracteristiques load(Long id) {		
		Caracteristiques caracteristiques = super.load(id);
		injectorHelper.injectDependencies(caracteristiques);
		return caracteristiques;
	}
}
