package com.degloba.domain.persistence.nosql.googleDatastore.api.objectify;


public interface Mapping<T extends EntityAggregateRoot, U extends EntityAggregateRoot> {
	 
	T fromObjectify(U entityObjectify);
 
	U toObjectify(T entity);
 
}