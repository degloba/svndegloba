/**
 * 
 */
package com.insacosa.domain.repositories;

import java.util.List;

import com.insacosa.domain.Caracteristiques;

import ddd.domain.annotations.DomainRepository;

/**
 * @author degloba
 * @category Repository lligat a l'entitat del Domini "Caracteristiques".
 * No està lligat a la tecnologia de persistencia
 */
@DomainRepository
public interface CaracteristiquesRepository {
	
	
	 /**
	   * Finds a cargo using given id.
	   *
	   * @param trackingId Id
	   * @return Caracteristiques if found, else {@code null}
	   */
	Caracteristiques find(Long id);

	  
	/**
	   * Finds all caracteristiques.
	   *
	   * @return All caracteristiques.
	   */
	public List<Caracteristiques> findAll();


	public Caracteristiques load(Long id);

	public Caracteristiques save(Caracteristiques entity);
}
