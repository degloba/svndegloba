/**
 * 
 */
package com.insacosa.domain.repositories;

import com.insacosa.domain.Usuaris;

import domain.annotations.DomainRepository;

/**
 * @author Slawek
 * 
 */
@DomainRepository
public interface UsuarisRepository {

	public Usuaris load(Long id);

	public Usuaris save(Usuaris entity);
}
