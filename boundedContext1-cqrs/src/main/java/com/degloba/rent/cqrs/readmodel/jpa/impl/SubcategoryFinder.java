package com.degloba.rent.cqrs.readmodel.jpa.impl;

import java.util.List;

import javax.inject.Inject;
// JPA
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

// Spring
import org.springframework.beans.factory.annotation.Qualifier;

// CQRS 
import com.degloba.rent.cqrs.readmodel.jpa.ISubcategoryFinder;
import com.degloba.rent.domain.jpa.Category;
import com.degloba.rent.domain.jpa.ISubcategoryRepository;
import com.degloba.rent.domain.jpa.Subcategory;
import com.degloba.cqrs.query.annotations.Finder;


@Finder
public class SubcategoryFinder implements ISubcategoryFinder {

    @PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactoryDatastore")
    private EntityManager entityManager;


	@Inject
	private ISubcategoryRepository subcategoryRepository;
    
    
	@SuppressWarnings("unchecked")
	@Override
	public List<Subcategory> findSubcategoriesByCategory(Category category) {
		// TODO Auto-generated method stub
				
		List<Subcategory> subcategories =  entityManager.createQuery("select s from com.degloba.rent.domain.Subcategory s where s.category = :category order by s.description")
					.setParameter("category", category).getResultList();
		
		entityManager.clear();
        entityManager.close();
        return subcategories;
		
	}
}
