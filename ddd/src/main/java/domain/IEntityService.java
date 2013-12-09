package domain;

import domain.annotations.DomainService;

/**
 * @author degloba
 *
 * @category Defineix el Servei de Domini NO lligat al Domini (BaseEntity i entitats que hereden)
 * 
 * @param <K>
 * @param <T>
 */
@DomainService
public interface IEntityService<K,T extends Entitat> {
	
	public void Add(T value);
	public void Remove(K id);
	public T Get(K id);

}
