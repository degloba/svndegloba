package com.degloba.rent.cqrs.readmodel;

import java.util.List;

import com.degloba.rent.domain.Category;
import com.degloba.rent.domain.Subcategory;

public interface SubcategoryFinder {

    List<Subcategory> findSubcategoriesByCategory(Category category);

}
