package com.degloba.lloguers.cqrs.readmodel.finders;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;

import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Categoria;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Foto;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Location;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Producte;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Propietari;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.SubCategoria;

/**
 * @category es {@link Finder} de lloguers implementat en
 * 
 * @author degloba
 *
 */
public class LloguerFinder implements ILloguerFinder {

	@PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactoryDatastore")
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
	@Override
    public List<Categoria> findCategories() {
        String jpql = "select c from com.degloba.rent.domain.persistence.rdbms.jpa.Category c";
        Query query = entityManager.createQuery(jpql);
        List<Categoria> categorias =  query.getResultList();
        entityManager.clear();
        entityManager.close();
        return categorias;
    }

	@Override
	public Categoria findCategoryBySubcategory(SubCategoria subCategoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producte> findProductsByOwner(Propietari propietari) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producte> findProductBySubcategory(SubCategoria subCategoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location findLocationByProduct(Producte producte) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Foto findPhotoByIdGcs(String idGcs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Foto> findPhotosBySubcategory(SubCategoria subCategoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Foto> findPhotosByProduct(Producte producte) {
		// TODO Auto-generated method stub
		return null;
	}

}
