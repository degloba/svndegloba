package com.degloba.rent.cqrs.readmodel.objectify;

import java.util.List;

import com.degloba.rent.domain.objectify.Photo;
import com.degloba.rent.domain.objectify.Product;
import com.degloba.rent.domain.objectify.Subcategory;


public interface ICategoryPhotoFinder {

	Photo findPhotoByIdGcs(String idGcs);

    List<Photo> findPhotosBySubcategory(Subcategory subcategory);
    
    List<Photo> findPhotosByProduct(Product product);
}
