package com.degloba.domain;

// DDD
import com.degloba.domain.IBaseAggregateRootDomainService;
import com.degloba.domain.jpa.BaseAggregateRoot;


/**
 * @author degloba
 *
 * @category Defineix el Servei de Domini lligat a l'entitat del Domini (BaseAggregateRoot)
 * 
 * @param <K>
 * @param <TEntity>
 */
public class BaseAggregateRootApplicationService<K,TEntity extends BaseAggregateRoot> implements IBaseAggregateRootApplicationService<K,TEntity> {

	private IBaseAggregateRootDomainService<K, TEntity> genericDomainService;
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
