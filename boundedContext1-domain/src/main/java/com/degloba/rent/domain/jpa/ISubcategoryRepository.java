package com.degloba.rent.domain.jpa;

import java.util.List;

import com.degloba.domain.annotations.DomainRepository;
import com.degloba.rent.domain.jpa.Subcategory;
import com.google.appengine.api.datastore.Key;


@DomainRepository
public interface ISubcategoryRepository {

	public Subcategory load(Key id);

	public void save(Subcategory subcategory);
	
	public List<Subcategory> getAll();
}
