package com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify;

import java.util.List;

import com.degloba.domain.annotations.DomainRepository;

import com.googlecode.objectify.Key;



@DomainRepository
public interface ISubcategoryRepository {

	public Subcategory load(Key<Subcategory> id);

	public void save(Subcategory subcategory);
	
	public List<Subcategory> getAll();
}
