package com.degloba.rent.domain.persistence.rdbms.jpa;

import java.util.List;

import com.degloba.domain.annotations.DomainRepository;
import com.degloba.domain.persistence.rdbms.jpa.IEntityRepository;


@DomainRepository
public interface IRentRepository extends IEntityRepository {

	public CategoryJpa loadCategory(long id);

	public void saveCategory(CategoryJpa category);
	
	public List<CategoryJpa> getAllCategories();
	
	public PhotoJpa loadPhoto(long id);

	public void savePhoto(PhotoJpa client);
	
	
}
