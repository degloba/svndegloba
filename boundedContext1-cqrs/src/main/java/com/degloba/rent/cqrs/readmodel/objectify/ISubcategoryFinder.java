package com.degloba.rent.cqrs.readmodel.objectify;

import java.util.List;
import java.util.Map;

import com.degloba.rent.domain.objectify.Category;
import com.degloba.rent.domain.objectify.Subcategory;



public interface ISubcategoryFinder {

    List<Subcategory> findSubcategoriesByCategory(Category category);
    //Map<String,String> findSubcategoriesByCategory(Category category);

}
