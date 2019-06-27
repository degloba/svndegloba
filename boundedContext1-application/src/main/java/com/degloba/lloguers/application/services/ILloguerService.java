package com.degloba.lloguers.application.services;

import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Categoria;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Foto;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Propietari;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.SubCategoria;


public interface ILloguerService {

	void creaCategoria(Categoria categoria);
	
    void creaFoto(Foto foto);
    
    void creaSubCategoria(SubCategoria subCategoria);
    
    void creaPropietari(Propietari propietari);
}
