/**
 * 
 */
package com.insacosa.Inmobles.domain.repositories;

import com.insacosa.Inmobles.domain.Tipus;

import domain.annotations.DomainRepository;

/**
 * @author Slawek
 * 
 */
@DomainRepository
public interface TipusRepository {

	public Tipus load(Long id);

	public Tipus save(Tipus entity);
}
