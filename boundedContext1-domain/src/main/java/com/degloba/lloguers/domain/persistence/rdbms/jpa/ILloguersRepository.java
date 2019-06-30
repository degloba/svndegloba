package com.degloba.lloguers.domain.persistence.rdbms.jpa;

import java.util.List;

import com.degloba.domain.annotations.DomainRepository;
import com.degloba.persistence.rdbms.jpa.IEntityRepository;

/**
 * @category Repositori : Lloguer
 */
@DomainRepository
public interface ILloguersRepository extends IEntityRepository {

	public Categoria loadCategory(long id);

	public void saveCategory(Categoria category);
	
	public List<Categoria> getAllCategories();
	
	public Foto loadPhoto(long id);

	public void savePhoto(Foto client);
	
	
}
