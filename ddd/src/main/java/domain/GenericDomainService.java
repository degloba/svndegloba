package domain;

import infrastructure.repository.jpa.GenericJpaRepository;


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
public class GenericDomainService<K,TEntity extends BaseAggregateRoot> implements IGenericDomainService<K,TEntity> {

	private GenericJpaRepository<K, TEntity> repositori;
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
