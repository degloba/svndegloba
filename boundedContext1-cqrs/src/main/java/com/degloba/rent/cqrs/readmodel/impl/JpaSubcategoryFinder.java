package com.degloba.rent.cqrs.readmodel.impl;

import java.util.List;

import javax.inject.Inject;
// JPA
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

// Spring
import org.springframework.beans.factory.annotation.Qualifier;

// CQRS 
import com.degloba.rent.cqrs.readmodel.CategoryFinder;
import com.degloba.rent.cqrs.readmodel.SubcategoryFinder;
import com.degloba.rent.domain.Category;
import com.degloba.rent.domain.ISubcategoryRepository;
import com.degloba.rent.domain.Subcategory;
import com.degloba.cqrs.query.annotations.Finder;


@Finder
public class JpaSubcategoryFinder implements SubcategoryFinder {

    @PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactoryDatastore")
    private EntityManager entityManager;


	@Inject
	private ISubcategoryRepository subcategoryRepository;
    
    
	@SuppressWarnings("unchecked")
	@Override
	public List<Subcategory> findSubcategoriesByCategory(Category category) {
		// TODO Auto-generated method stub
		
		return entityManager.createQuery("select s from com.degloba.rent.domain.Subcategory s where s.category = :category order by s.description")
					.setParameter("category", category).getResultList();
	
	}
}
