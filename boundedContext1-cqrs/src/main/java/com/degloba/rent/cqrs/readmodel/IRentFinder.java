package com.degloba.rent.cqrs.readmodel;

import java.util.List;

import com.degloba.rent.domain.persistence.rdbms.jpa.Category;
import com.degloba.rent.domain.persistence.rdbms.jpa.Location;
import com.degloba.rent.domain.persistence.rdbms.jpa.Owner;
import com.degloba.rent.domain.persistence.rdbms.jpa.Photo;
import com.degloba.rent.domain.persistence.rdbms.jpa.Product;
import com.degloba.rent.domain.persistence.rdbms.jpa.Subcategory;

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
