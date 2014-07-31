package domain;

import domain.annotations.DomainService;
import domain.support.Entitat;

/**
 * @author degloba
 *
 * @category Defineix el Servei de Domini NO lligat al Domini (BaseEntity i entitats que hereden)
 * 
 * @param <K>
 * @param <T>
 */
@DomainService
public interface IGenericDomainService<K,T extends Entitat> {
	
	public void Add(T value);
	public void Remove(K id);
	public T Get(K id);

}
