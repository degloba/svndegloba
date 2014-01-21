/**
 * 
 */
package com.insacosa.Inmobles.domain.repositories;

import com.insacosa.Inmobles.domain.Inmobles;

import domain.annotations.DomainRepository;

/**
 * @author Slawek
 * 
 */
@DomainRepository
public interface InmoblesRepository {

	public Inmobles load(Long id);

	public Inmobles save(Inmobles entity);
	
	public void delete(Long id);
}
