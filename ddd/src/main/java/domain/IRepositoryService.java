package domain;

import java.util.List;


/**
 * @author degloba
 * 
 * @category Defineix patro Repository.
 * Abstraccio de l'infraestructura de persistencia
 * No lligat a les entitats de Domini
 * 
 * @param <K> = Tipus d'identificador
 * @param <E> = Tipus d'entitat que gestiona
 */
public interface IRepositoryService<K, E> {  
	
	E save(E entity);          
	void remove(E entity);     
	E findById(K id);     
	List<E> findAll();     
	Long getTotalResult();
	E update(E entity);
	
	
}