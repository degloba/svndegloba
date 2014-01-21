/**
 * 
 */
package com.insacosa.Inmobles.domain.repositories;

import com.insacosa.Inmobles.domain.Ciutats;

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
