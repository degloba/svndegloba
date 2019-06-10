package com.degloba.rent.facade.jpa;

import com.degloba.lloguer.domain.persistence.nosql.googleDatastore.api.objectify.Category;

/**
 * @author degloba
 */
public interface CategoryFacade {

    void createCategory(Category category);

}
