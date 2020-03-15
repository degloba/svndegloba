package com.degloba.lloguers.cqrs.queries.finders;

import java.util.List;

import com.degloba.cqrs.query.annotations.Finder;
import com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify.Categoria;
import com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify.Foto;
import com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify.Location;
import com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify.Producte;
import com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify.Propietari;
import com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify.SubCategoria;

/**
 * @category {@link Finder} de lloguer
 * 
 * @author degloba
 *
 */
@Finder
public interface ILloguerFinder {
	
    List<Categoria> findCategories();

    Categoria findCategoryBySubcategory(SubCategoria subCategoria);
    
    List<Producte> findProductsByOwner(Propietari propietari);

    List<Producte> findProductBySubcategory(SubCategoria subCategoria);
    
	Location findLocationByProduct(Producte producte);
	
	Foto findPhotoByIdGcs(String idGcs);

    List<Foto> findPhotosBySubcategory(SubCategoria subCategoria);
    
    List<Foto> findPhotosByProduct(Producte producte);

}
