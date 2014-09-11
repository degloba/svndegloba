/**
 * 
 */
package com.insacosa.Inmobles.infrastructure.repositories.jpa;

import java.util.List;

import javax.inject.Inject;

import domain.annotations.DomainRepositoryImpl;
import domain.support.InjectorHelper;
import infrastructure.repo.jpa.GenericJpaRepositoryForBaseEntity;


import com.insacosa.Inmobles.domain.Caracteristiques;
import com.insacosa.Inmobles.domain.repositories.CaracteristiquesRepository;

/**
 * @author degloba
 * @category Implementació d'un Repository JPA lligat a una entitat de domini (caracteristiques)
 */
@DomainRepositoryImpl
public class JpaCaracteristiquesRepository extends GenericJpaRepositoryForBaseEntity<Caracteristiques> implements CaracteristiquesRepository{

	@Inject
	private InjectorHelper injectorHelper;
	
	
	public Caracteristiques load(Long id) {		
		Caracteristiques caracteristiques = super.load(id);
		injectorHelper.injectDependencies(caracteristiques);
		return caracteristiques;
	}

	
	public Caracteristiques find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Caracteristiques> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	public InjectorHelper getInjectorHelper() {
		return injectorHelper;
	}


	public void setInjectorHelper(InjectorHelper injectorHelper) {
		this.injectorHelper = injectorHelper;
	}
	
	
}