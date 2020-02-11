package com.degloba.lloguers.facade.jpa;

import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Categoria;

/**
 * @category
 * 
 * @author degloba
 */
public interface CategoriaFacade {

    void creaCategoria(Categoria categoria);

}
