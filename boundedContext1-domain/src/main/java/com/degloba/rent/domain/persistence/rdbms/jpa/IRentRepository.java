package com.degloba.rent.domain.persistence.rdbms.jpa;

import java.util.List;

import com.degloba.domain.annotations.DomainRepository;


@DomainRepository
public interface IRentRepository {

	public CategoryJpa loadCategory(long id);

	public void saveCategory(CategoryJpa category);
	
	public List<CategoryJpa> getAllCategories();
	
	public PhotoJpa loadPhoto(long id);

	public void savePhoto(PhotoJpa client);
	
	
}
