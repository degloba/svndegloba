package com.degloba.rent.cqrs.readmodel;

import java.util.List;

import com.degloba.cqrs.query.annotations.Finder;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Category;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Location;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Owner;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Photo;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Product;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Subcategory;

@Finder
public interface IRentFinder {
	

    List<Category> findCategories();

    Category findCategoryBySubcategory(Subcategory subcategory);
    
    List<Product> findProductsByOwner(Owner owner);

    List<Product> findProductBySubcategory(Subcategory subcategory);
    
	Location findLocationByProduct(Product product);
	
	Photo findPhotoByIdGcs(String idGcs);

    List<Photo> findPhotosBySubcategory(Subcategory subcategory);
    
    List<Photo> findPhotosByProduct(Product product);

}
