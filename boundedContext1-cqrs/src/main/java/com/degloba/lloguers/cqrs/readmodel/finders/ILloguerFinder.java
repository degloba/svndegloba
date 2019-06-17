package com.degloba.lloguers.cqrs.readmodel.finders;

import java.util.List;

import com.degloba.cqrs.query.annotations.Finder;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Category;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Foto;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Location;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Producte;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Propietari;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Subcategory;

@Finder
public interface ILloguerFinder {
	
    List<Category> findCategories();

    Category findCategoryBySubcategory(Subcategory subcategory);
    
    List<Producte> findProductsByOwner(Propietari propietari);

    List<Producte> findProductBySubcategory(Subcategory subcategory);
    
	Location findLocationByProduct(Producte producte);
	
	Foto findPhotoByIdGcs(String idGcs);

    List<Foto> findPhotosBySubcategory(Subcategory subcategory);
    
    List<Foto> findPhotosByProduct(Producte producte);

}
