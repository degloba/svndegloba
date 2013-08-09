package ddd.domain;


/**
 * @author degloba
 *
 * @category Defineix el Servei de Domini lligat a l'entitat del Domini (BaseEntity)
 * 
 * @param <K>
 * @param <TEntity>
 */
public class EntityService<K,TEntity extends BaseEntity> implements IEntityService<K,TEntity> {


	public EntityService(RepositoryService<Long, TEntity> _repositori) {
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void Add(TEntity value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Remove(K id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TEntity Get(K id) {
		// TODO Auto-generated method stub
		return null;
	}
}
