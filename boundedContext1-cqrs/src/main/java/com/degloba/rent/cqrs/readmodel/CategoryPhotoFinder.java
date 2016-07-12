package com.degloba.rent.cqrs.readmodel;

import java.util.List;

import com.degloba.rent.domain.jpa.Category;
import com.degloba.rent.domain.jpa.Photo;
import com.degloba.rent.domain.jpa.Product;
import com.degloba.rent.domain.jpa.Subcategory;

public interface CategoryPhotoFinder {

	Photo findPhotoByIdGcs(String idGcs);

    List<Photo> findPhotosBySubcategory(Subcategory subcategory);
    
    List<Photo> findPhotosByProduct(Product product);
}
