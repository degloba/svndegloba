/**
 * 
 */
package com.insacosa.domain.repositories;

import com.insacosa.domain.Customer;
import com.insacosa.domain.Inmobles;

import ddd.domain.annotations.DomainRepository;

/**
 * @author Slawek
 * 
 */
@DomainRepository
public interface InmoblesRepository {

	public Inmobles load(Long id);

	public Inmobles save(Inmobles entity);
}
