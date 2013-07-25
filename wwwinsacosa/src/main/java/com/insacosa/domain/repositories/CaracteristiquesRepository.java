/**
 * 
 */
package com.insacosa.domain.repositories;

import com.insacosa.domain.Caracteristiques;
import com.insacosa.domain.Solicituds;

import ddd.domain.annotations.DomainRepository;

/**
 * @author Slawek
 * 
 */
@DomainRepository
public interface CaracteristiquesRepository {

	public Caracteristiques load(Long id);

	public Caracteristiques save(Caracteristiques entity);
}
