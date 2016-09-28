package com.degloba.rent.cqrs.readmodel.objectify.impl;

import java.util.List;

import javax.inject.Inject;


// Spring
import org.springframework.beans.factory.annotation.Qualifier;

// CQRS 

import com.degloba.rent.cqrs.readmodel.objectify.ISubcategoryFinder;
import com.degloba.rent.cqrs.readmodel.objectify.impl.SubcategoryFinder;
import com.degloba.rent.domain.objectify.Category;
import com.degloba.rent.domain.objectify.ISubcategoryRepository;
import com.degloba.rent.domain.objectify.Subcategory;
import com.degloba.cqrs.query.annotations.Finder;


@Finder
public class SubcategoryFinder implements ISubcategoryFinder {


	@Inject
	private ISubcategoryRepository subcategoryRepository;
    

	@Override
	public List<Subcategory> findSubcategoriesByCategory(Category category) {

        return null;
		
	}
}
