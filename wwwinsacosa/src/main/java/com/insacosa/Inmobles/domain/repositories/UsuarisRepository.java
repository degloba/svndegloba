/**
 * 
 */
package com.insacosa.Inmobles.domain.repositories;

import com.insacosa.Inmobles.domain.Usuaris;

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
