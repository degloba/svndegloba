package domain;

import infrastructure.repository.jpa.IBaseAggregateRootJpaRepository;

//DDD
import domain.support.BaseAggregateRoot;


/**
 * @author degloba
 *
 * @category Defineix el Servei de Domini lligat a l'entitat del Domini (BaseEntity)
 * 
 * @param <K>
 * @param <TEntity>
 */
public class BaseAggregateRootDomainService<K,TEntity extends BaseAggregateRoot> implements IBaseAggregateRootDomainService<K,TEntity> {

	private IBaseAggregateRootJpaRepository<K> repositori;
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
