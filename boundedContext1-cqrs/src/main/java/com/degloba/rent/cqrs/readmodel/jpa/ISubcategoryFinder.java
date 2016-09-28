package com.degloba.rent.cqrs.readmodel.jpa;

import java.util.List;
import java.util.Map;

import com.degloba.rent.domain.jpa.Category;
import com.degloba.rent.domain.jpa.Subcategory;

public interface ISubcategoryFinder {

    List<Subcategory> findSubcategoriesByCategory(Category category);
    //Map<String,String> findSubcategoriesByCategory(Category category);

}
