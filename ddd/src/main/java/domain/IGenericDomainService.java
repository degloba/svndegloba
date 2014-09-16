package domain;

// DDD
import domain.annotations.DomainService;
import domain.support.BaseAggregateRoot;
import domain.support.BaseEntity;


/**
 * @author degloba
 *
 * @category Defineix el Servei de Domini NO lligat al Domini (BaseEntity i entitats que hereden)
 * 
 * @param <K>
 * @param <T>
 */
@DomainService
public interface IGenericDomainService<K,T extends BaseAggregateRoot> {
	
	public void Add(T value);
	public void Remove(K id);
	public T Get(K id);

}
