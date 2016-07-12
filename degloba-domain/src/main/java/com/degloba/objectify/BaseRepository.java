package com.degloba.objectify;

public interface BaseRepository<T extends EntityAggregateRoot> {
	 
	Long put(T entity);
 
	T get(Long id);
 
	void delete(T entity);
 
}
