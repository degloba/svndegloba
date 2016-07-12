package com.degloba.objectify;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Query;
import com.googlecode.objectify.util.DAOBase;

public abstract class AbstractRepository<T extends EntityAggregateRoot,	U extends EntityAggregateRootObjectify, V extends Mapping<T, U>>
				extends DAOBase implements BaseRepository<T> {

	protected V mapping;
	private Class<U> entityAggregateRootObjectifyClass;
	
	protected AbstractRepository(V mapping,	Class<U> entityAggregateRootObjectifyClass) {
			super();
			this.mapping = mapping;
			this.entityAggregateRootObjectifyClass = entityAggregateRootObjectifyClass;
		}
	
	/*public Long put(T entity) {
		U entityObjectify = mapping.toObjectify(entity);
		ofy().put(entityObjectify);
		entity.setId(entityObjectify.getId());
		return entityObjectify.getId();
	}
	
	public void delete(T entity){
		U entityObjectify = mapping.toObjectify(entity);
		ofy().delete(entityObjectify);
	}
	
	public T get(Long id) {
		U entityObjectify = ofy().get(entityAggregateRootObjectifyClass, id);
		T entity = mapping.fromObjectify(entityObjectify);
		return this.handleAssociations(entity, entityObjectify);
	}
	
	protected T handleAssociations(T entity, U entityObjectify) {
		return entity;
	}
	
	protected List<T> getEntities(QueryCallback<U> queryCallback) {
		List<T> entityList = new ArrayList<T>();
		Query<U> query = ofy().query(entityAggregateRootObjectifyClass);
		query = queryCallback.manipulateQuery(query);
		for (U entityObjectify : query) {
			T entity = mapping.fromObjectify(entityObjectify);
			entityList.add(this.handleAssociations(entity, entityObjectify));
		}
		return entityList;
	}*/
 
	protected interface QueryCallback<U extends EntityAggregateRootObjectify> {
 
		public Query<U> manipulateQuery(Query<U> query);
 
	}
}
