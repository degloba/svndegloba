package com.degloba.rent.cqrs.readmodel;

import java.util.List;
import java.util.Map;

import com.degloba.rent.domain.jpa.Category;
import com.degloba.rent.domain.jpa.Subcategory;

public interface SubcategoryFinder {

    List<Subcategory> findSubcategoriesByCategory(Category category);
    //Map<String,String> findSubcategoriesByCategory(Category category);

}
