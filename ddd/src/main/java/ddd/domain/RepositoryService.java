package ddd.domain;

import java.util.List;


/**
 * @author degloba
 * @category Defineix patró Repository.
 * Abstracció de l'infraestructura de persistència
 * 
 * @param <K> = Tipus d'identificador
 * @param <E> = Tipus d'entitat que gestiona
 */
public interface RepositoryService<K, E> {  
	
	E save(E entity);          
	void remove(E entity);     
	E findById(K id);     
	List<E> findAll();     
	Long getTotalResult();
	E update(E entity);
	
}