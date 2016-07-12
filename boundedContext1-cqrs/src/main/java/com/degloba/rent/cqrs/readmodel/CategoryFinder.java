package com.degloba.rent.cqrs.readmodel;

import java.util.List;

import com.degloba.rent.domain.jpa.Category;
import com.degloba.rent.domain.jpa.Subcategory;

public interface CategoryFinder {

    List<Category> findCategories();

    Category findCategoryBySubcategory(Subcategory subcategory);
}
