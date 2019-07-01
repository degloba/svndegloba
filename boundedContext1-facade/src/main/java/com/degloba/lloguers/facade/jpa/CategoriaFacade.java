package com.degloba.lloguers.facade.jpa;

import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Categoria;

/**
 * @author degloba
 */
public interface CategoryFacade {

    void createCategory(Categoria categoria);

}
