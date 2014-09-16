package application;

// DDD
import domain.IGenericDomainService;
import domain.support.BaseAggregateRoot;
import domain.support.BaseEntity;


/**
 * @author degloba
 *
 * @category Defineix el Servei de Domini lligat a l'entitat del Domini (BaseEntity)
 * 
 * @param <K>
 * @param <TEntity>
 */
public class GenericApplicationService<K,TEntity extends BaseAggregateRoot> implements IGenericApplicationService<K,TEntity> {

	private IGenericDomainService<K, TEntity> genericDomainService;
/*	private Sys.ISys sysoperations;
	private Log.Ilog logService; 
	private Core.ICore coreService;
	private Integration.IIntegration integrationService;*/
	
	
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
