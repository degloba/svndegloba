package application;

import domain.support.Entitat;


/**
 * @author degloba
 *
 * @category Defineix el Servei de Domini lligat a l'entitat del Domini (BaseEntity)
 * 
 * @param <K>
 * @param <TEntity>
 */
public class GenericApplicationService<K,TEntity extends Entitat> implements IGenericApplicationService<K,TEntity> {

	
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
