/**
 * 
 */
package com.insacosa.Inmobles.domain.repositories;

import com.insacosa.Inmobles.domain.Solicituds;

import domain.annotations.DomainRepository;

/**
 * @author Slawek
 * 
 */
@DomainRepository
public interface SolicitudsRepository {

	public Solicituds load(Long id);

	public Solicituds save(Solicituds entity);

	public void persist(Solicituds solicitud);
}
