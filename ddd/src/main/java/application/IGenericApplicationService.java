package application;

import domain.annotations.DomainService;
import domain.support.BaseEntity;

/**
 * @author degloba
 *
 * @category Defineix el Servei d'aplicacio NO lligat al Domini (BaseEntity i entitats que hereden)
 * 
 * @param <K>
 * @param <T>
 */
@DomainService
public interface IGenericApplicationService<K,T extends BaseEntity> {
	

	public void Add(T value);
	public void Remove(K id);
	public T Get(K id);

}