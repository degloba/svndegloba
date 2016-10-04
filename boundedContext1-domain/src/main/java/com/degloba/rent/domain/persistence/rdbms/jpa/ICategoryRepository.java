package com.degloba.rent.domain.persistence.rdbms.jpa;

import java.util.List;

import com.degloba.domain.annotations.DomainRepository;


@DomainRepository
public interface ICategoryRepository {

	public Category load(long id);

	public void save(Category category);
	
	public List<Category> getAll();
}
