package com.degloba.rent.cqrs.readmodel.objectify;

import java.util.List;

import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Photo;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Product;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Subcategory;


public interface IPhotoFinder {

	Photo findPhotoByIdGcs(String idGcs);

    List<Photo> findPhotosBySubcategory(Subcategory subcategory);
    
    List<Photo> findPhotosByProduct(Product product);
}
