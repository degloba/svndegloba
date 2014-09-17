package domain;

import infrastructure.repository.jpa.IBaseAggregateRootJpaRepository;


import domain.support.BaseAggregateRoot;
// DDD
import domain.support.BaseEntity;


/**
 * @author degloba
 *
 * @category Defineix el Servei de Domini lligat a l'entitat del Domini (BaseEntity)
 * 
 * @param <K>
 * @param <TEntity>
 */
public class BaseAggregateRootDomainService<K,TEntity extends BaseAggregateRoot> implements IBaseAggregateRootDomainService<K,TEntity> {

	private IBaseAggregateRootJpaRepository<K, TEntity> repositori;
	//private IViewsGenericRepository views;
	
	public void Add(TEntity value) {
		// TODO Auto-generated method stub
		
	}

	public void Remove(K id) {
		// TODO Auto-generated method stub
		
	}

	public TEntity Get(K id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*public EntityService CreateService(TEntity entitat) {
		
	}
	*/
}
