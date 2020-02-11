package com.degloba.lloguers.facade.ui;

import com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify.Categoria;

/**
 * @category
 * 
 * @author degloba
 */
public interface ICategoriaFacade {

    void creaCategoria(Categoria categoria);

}
