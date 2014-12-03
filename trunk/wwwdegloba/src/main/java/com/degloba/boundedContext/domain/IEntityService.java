package com.degloba.boundedContext.domain;

import domain.seedwork.*;


public interface IEntityService<E extends Entity> {
	
	public void Add(E value);
	public void remove(E value);
	public void removeById(E value);
	public void update(E value);
	public void merge(E persisted, E current);
	
	public IRepository<?> repositori=null;

}
