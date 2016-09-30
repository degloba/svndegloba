package com.degloba.domain;

// Domain
import com.degloba.domain.annotations.DomainService;
import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;


/**
 * @author degloba
 *
 * @category Defineix el Servei d'aplicacio NO lligat al Domini (BaseEntity i entitats que hereden)
 * 
 * @param <K>
 * @param <T>
 */
@DomainService
public interface IBaseAggregateRootApplicationService<K,T extends BaseAggregateRoot> {
	

	public void Add(T value);
	public void Remove(K id);
	public T Get(K id);

}
