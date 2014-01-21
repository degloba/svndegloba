/**
 * 
 */
package com.insacosa.Inmobles.domain.repositories;

import com.insacosa.Inmobles.domain.Provincies;

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
