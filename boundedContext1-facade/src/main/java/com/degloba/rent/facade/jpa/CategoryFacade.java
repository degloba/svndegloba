package com.degloba.rent.facade.jpa;

import com.degloba.rent.domain.persistence.rdbms.jpa.Category;

/**
 * @author degloba
 */
public interface CategoryFacade {

    void createCategory(Category category);

}
