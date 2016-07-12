package com.degloba.objectify;

public interface Mapping<T extends EntityAggregateRoot, U extends EntityAggregateRootObjectify> {
	 
	T fromObjectify(U entityObjectify);
 
	U toObjectify(T entity);
 
}