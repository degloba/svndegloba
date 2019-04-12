package com.degloba.rent.facade.objectify;

import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Category;

/**
 * @author degloba
 */
public interface CategoryFacade {

    void createCategory(Category category);

}
