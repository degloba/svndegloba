package com.degloba.rent.cqrs.readmodel.finders;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;


import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Category;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Location;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Owner;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Photo;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Product;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Subcategory;

// Entitats NoSql/GoogleDatastore (Api JPA)



// Entitats NoSql/GoogleDatastore (Api Objectify)
//import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Category;
//import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Location;
//import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Owner;
//import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Photo;
//import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Product;
//import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Subcategory;

// Entitats Rdbms (Api JPA)
// ..............


// Entitats NoSql/MongoDB (Api xxx)
// ...............


public class RentFinder implements IRentFinder {

	@PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactoryDatastore")
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
	@Override
    public List<Category> findCategories() {
        String jpql = "select c from com.degloba.rent.domain.persistence.rdbms.jpa.CategoryJpa c";
        Query query = entityManager.createQuery(jpql);
        List<Category> categories =  query.getResultList();
        entityManager.clear();
        entityManager.close();
        return categories;
    }

	@Override
	public Category findCategoryBySubcategory(Subcategory subcategory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findProductsByOwner(Owner owner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findProductBySubcategory(Subcategory subcategory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location findLocationByProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Photo findPhotoByIdGcs(String idGcs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Photo> findPhotosBySubcategory(Subcategory subcategory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Photo> findPhotosByProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

}
