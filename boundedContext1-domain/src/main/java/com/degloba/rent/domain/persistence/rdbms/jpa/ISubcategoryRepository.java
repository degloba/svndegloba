package com.degloba.rent.domain.persistence.rdbms.jpa;

import java.util.List;

import com.degloba.domain.annotations.DomainRepository;


@DomainRepository
public interface ISubcategoryRepository {

	public Subcategory load(long id);

	public void save(Subcategory subcategory);
	
	public List<Subcategory> getAll();
}
