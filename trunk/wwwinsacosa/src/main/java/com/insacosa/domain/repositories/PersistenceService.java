package com.insacosa.domain.repositories;

import java.util.List;


/**
 * @author degloba
 * @category Defineix patró UOW + Repository
 * @param <K> = Tipus d'identificador
 * @param <E> = Tipus d'entitat que gestiona
 */
public interface PersistenceService<K, E> {  
	
	E save(E entity);     
	E update(E entity);     
	void remove(E entity);     
	E findById(K id);     
	List<E> findAll();     
	Long getTotalResult();
	
}