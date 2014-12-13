package com.degloba.boundedContext.casino.domain;

import domain.seedwork.*;
import domain.support.BaseAggregateRoot;


public interface IEntityService<E> {
	
	public void Add(E value);
	public void remove(E value);
	public void removeById(E value);
	public void update(E value);
	public void merge(E persisted, E current);
	
	public IRepository<?> repositori=null;

}
