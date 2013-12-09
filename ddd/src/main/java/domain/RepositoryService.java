package domain;

import java.util.List;


/**
 * @author degloba
 * @category Defineix patr� Repository.
 * Abstracci� de l'infraestructura de persist�ncia
 * No lligat a les entitats de Domini
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