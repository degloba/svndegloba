package com.degloba.lloguers.domain.persistence.rdbms.jpa;

import java.util.List;

import com.degloba.domain.annotations.DomainRepository;
import com.degloba.persistence.rdbms.jpa.IEntityRepository;

/**
 * @category Repositori : Lloguer
 */
@DomainRepository
public interface ILloguerRepository extends IEntityRepository {

	public CategoryJpa loadCategory(long id);

	public void saveCategory(CategoryJpa category);
	
	public List<CategoryJpa> getAllCategories();
	
	public PhotoJpa loadPhoto(long id);

	public void savePhoto(PhotoJpa client);
	
	
}
