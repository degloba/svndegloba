package com.degloba.lloguers.application.services;

import javax.inject.Inject;

import com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify.Categoria;
import com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify.Foto;
import com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify.ILloguerRepository;
import com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify.Propietari;
import com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify.SubCategoria;

import lombok.Value;

/**
 * @category servei de lloguer
 * 
 * @author degloba
 *
 */
@Value
public class LloguerService implements ILloguerService {
	
/*	@Inject
	private IRentRepository rentRepositoryJpa;*/
	
	
	@Inject
	private ILloguerRepository lloguerRepositoryObjectify;



	@Override
	public void creaCategoria(Categoria categoria) {
		lloguerRepositoryObjectify.create(categoria);
	}
	
	
	@Override
	public void creaFoto(Foto foto) {
		lloguerRepositoryObjectify.create(foto);
	}

	
	@Override
	public void creaPropietari(Propietari propietari) {
		lloguerRepositoryObjectify.create(propietari);

	}

	@Override
	public void creaSubcategoria(SubCategoria subCategoria) {
		// TODO Auto-generated method stub
		
	}



}
