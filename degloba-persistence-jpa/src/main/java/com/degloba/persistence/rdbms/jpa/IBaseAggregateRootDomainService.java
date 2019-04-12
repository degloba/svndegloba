package com.degloba.persistence.rdbms.jpa;

// DDD
import com.degloba.domain.annotations.DomainService;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;



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
