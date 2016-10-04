package com.degloba.rent.facade.objectify;

import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Subcategory;

/**
 * @author degloba
 */
public interface SubcategoryFacade {

    void createSubcategory(Subcategory subcategory);

}
