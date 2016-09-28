package com.degloba.rent.cqrs.readmodel.jpa;

import java.util.List;

import com.degloba.rent.domain.jpa.Category;
import com.degloba.rent.domain.jpa.Subcategory;

public interface ICategoryFinder {

    List<Category> findCategories();

    Category findCategoryBySubcategory(Subcategory subcategory);
}
