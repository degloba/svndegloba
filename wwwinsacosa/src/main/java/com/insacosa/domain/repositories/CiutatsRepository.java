/**
 * 
 */
package com.insacosa.domain.repositories;

import com.insacosa.domain.Ciutats;

import domain.annotations.DomainRepository;

/**
 * @author Slawek
 * 
 */
@DomainRepository
public interface CiutatsRepository {

	public Ciutats load(Long id);

	public Ciutats save(Ciutats entity);
}
