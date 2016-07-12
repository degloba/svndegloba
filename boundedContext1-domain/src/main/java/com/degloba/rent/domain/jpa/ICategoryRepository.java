package com.degloba.rent.domain.jpa;

import java.util.List;

import com.degloba.domain.annotations.DomainRepository;
import com.degloba.rent.domain.jpa.Category;
import com.google.appengine.api.datastore.Key;


@DomainRepository
public interface ICategoryRepository {

	public Category load(Key id);

	public void save(Category category);
	
	public List<Category> getAll();
}
