package com.degloba.domain;

// DDD
import com.degloba.domain.annotations.DomainService;
import com.degloba.domain.BaseAggregateRoot;


/**
 * @author degloba
 *
 * @category Defineix el Servei de Domini NO lligat al Domini (BaseAggregateRoot i entitats que hereden)
 * 
 * @param <K>
 * @param <E>
 */
@DomainService
public interface IBaseAggregateRootDomainService<K,E extends BaseAggregateRoot> {
	
	public void Add(E value);
	public void Remove(K id);
	public E Get(K id);

}
