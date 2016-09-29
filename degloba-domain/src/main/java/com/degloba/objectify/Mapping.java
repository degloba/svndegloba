package com.degloba.objectify;

import com.degloba.domain.datastore.EntityAggregateRoot;

public interface Mapping<T extends EntityAggregateRoot, U extends EntityAggregateRootObjectify> {
	 
	T fromObjectify(U entityObjectify);
 
	U toObjectify(T entity);
 
}