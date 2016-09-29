package com.degloba.domain.datastore;

public interface Mapping<T extends EntityAggregateRoot, U extends EntityAggregateRoot> {
	 
	T fromObjectify(U entityObjectify);
 
	U toObjectify(T entity);
 
}