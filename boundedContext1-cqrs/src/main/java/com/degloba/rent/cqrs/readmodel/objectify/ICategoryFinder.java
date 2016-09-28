package com.degloba.rent.cqrs.readmodel.objectify;

import java.util.List;

import com.degloba.rent.domain.objectify.Category;
import com.degloba.rent.domain.objectify.Subcategory;


public interface ICategoryFinder {

    List<Category> findCategories();

    Category findCategoryBySubcategory(Subcategory subcategory);
}
