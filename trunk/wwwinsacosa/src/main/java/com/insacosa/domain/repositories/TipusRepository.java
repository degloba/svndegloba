/**
 * 
 */
package com.insacosa.domain.repositories;

import com.insacosa.domain.Tipus;

import ddd.domain.annotations.DomainRepository;

/**
 * @author Slawek
 * 
 */
@DomainRepository
public interface TipusRepository {

	public Tipus load(Long id);

	public Tipus save(Tipus entity);
}
