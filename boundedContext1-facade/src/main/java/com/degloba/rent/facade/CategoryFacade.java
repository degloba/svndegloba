package com.degloba.rent.facade;

import com.degloba.rent.domain.jpa.Category;
import com.degloba.rent.domain.jpa.Subcategory;


/**
 * @author degloba
 */
public interface CategoryFacade {

    void createCategory(Category category);

}
