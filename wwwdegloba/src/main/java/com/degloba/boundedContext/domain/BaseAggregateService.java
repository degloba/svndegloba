package com.degloba.boundedContext.domain;

public class BaseAggregateService<K> implements IBaseAggregateService<K> {

	IBaseAggregateRootRepository<K> i;
}
