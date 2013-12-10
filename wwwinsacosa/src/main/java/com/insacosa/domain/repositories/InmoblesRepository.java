/**
 * 
 */
package com.insacosa.domain.repositories;

import com.insacosa.domain.Inmobles;

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
