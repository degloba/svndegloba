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
public class BaseAggregateRootDomainService<K,E extends BaseAggregateRoot> implements IBaseAggregateRootDomainService<K,E> {

	private IBaseAggregateRootJpaRepository<K,E> repositori;
	//private IViewsGenericRepository views;
	
	public void Add(E value) {
		// TODO Auto-generated method stub
		
	}

	public void Remove(K id) {
		// TODO Auto-generated method stub
		
	}

	public E Get(K id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*public EntityService CreateService(TEntity entitat) {
		
	}
	*/
}
