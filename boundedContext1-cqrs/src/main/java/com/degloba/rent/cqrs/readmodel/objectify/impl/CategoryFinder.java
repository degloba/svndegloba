package com.degloba.rent.cqrs.readmodel.objectify.impl;

import java.util.List;


// Spring
import org.springframework.beans.factory.annotation.Qualifier;

// CQRS 
import com.degloba.rent.cqrs.readmodel.objectify.ICategoryFinder;
import com.degloba.rent.domain.objectify.Category;
import com.degloba.rent.domain.objectify.Subcategory;
import com.degloba.cqrs.query.annotations.Finder;


@Finder
public class CategoryFinder implements ICategoryFinder {


    @SuppressWarnings("unchecked")
	@Override
    public List<Category> findCategories() {

        return null;
    }

	@Override
	public Category findCategoryBySubcategory(Subcategory subcategory) {
		// TODO Auto-generated method stub
		return null;
	}
}
