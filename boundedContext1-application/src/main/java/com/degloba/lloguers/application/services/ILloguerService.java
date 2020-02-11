package com.degloba.lloguers.application.services;

import com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify.Categoria;
import com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify.Foto;
import com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify.Propietari;
import com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify.SubCategoria;

/**
 * 
 * @author degloba
 *
 */
public interface ILloguerService {

	void creaCategoria(Categoria categoria);
	
    void creaFoto(Foto foto);
    
    void creaSubcategoria(SubCategoria subCategoria);
    
    void creaPropietari(Propietari propietari);
}
