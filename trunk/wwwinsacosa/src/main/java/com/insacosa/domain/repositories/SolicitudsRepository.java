/**
 * 
 */
package com.insacosa.domain.repositories;

import com.insacosa.domain.Solicituds;

import ddd.domain.annotations.DomainRepository;

/**
 * @author Slawek
 * 
 */
@DomainRepository
public interface SolicitudsRepository {

	public Solicituds load(Long id);

	public Solicituds save(Solicituds entity);
}
