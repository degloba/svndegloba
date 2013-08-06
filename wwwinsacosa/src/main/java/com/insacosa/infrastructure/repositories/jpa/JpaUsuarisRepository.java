/**
 * 
 */
package com.insacosa.infrastructure.repositories.jpa;

import javax.inject.Inject;

import ddd.domain.annotations.DomainRepositoryImpl;
import ddd.domain.support.InjectorHelper;
import ddd.infrastructure.repo.jpa.GenericJpaRepositoryForBaseEntity;


import com.insacosa.domain.Usuaris;
import com.insacosa.domain.repositories.UsuarisRepository;

/**
 * @author degloba
 * @category Implementació d'un Repository JPA lligat a una entitat de domini (Usuaris)
 */
@DomainRepositoryImpl
public class JpaUsuarisRepository extends GenericJpaRepositoryForBaseEntity<Usuaris> implements UsuarisRepository{

	@Inject
	private InjectorHelper injectorHelper;
	
	@Override
	public Usuaris load(Long id) {		
		Usuaris usuaris = super.load(id);
		injectorHelper.injectDependencies(usuaris);
		return usuaris;
	}
}
