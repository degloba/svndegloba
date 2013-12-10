/**
 * 
 */
package com.insacosa.domain.repositories;

import com.insacosa.domain.Provincies;

import domain.annotations.DomainRepository;

/**
 * @author Slawek
 * 
 */
@DomainRepository
public interface ProvinciesRepository {

	public Provincies load(Long id);

	public Provincies save(Provincies entity);
}
