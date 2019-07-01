package com.degloba.lloguers.facade.jpa;

import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.SubCategoria;

/**
 * @author degloba
 */
public interface SubcategoryFacade {

    void createSubcategory(SubCategoria subCategoria);

}
