package domain;


/**
 * @author degloba
 *
 * @category Defineix el Servei de Domini lligat a l'entitat del Domini (BaseEntity)
 * 
 * @param <K>
 * @param <TEntity>
 */
public class GenericService<K,TEntity extends BaseAggregateRoot> implements IGenericService<K,TEntity> {


	public GenericService(RepositoryService<Long, TEntity> _repositori) {
		// TODO Auto-generated constructor stub
	}

	
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
}
