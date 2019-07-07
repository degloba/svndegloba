package com.degloba.lloguers.application.services;

import javax.inject.Inject;

import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Categoria;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Foto;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.ILloguerRepository;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Propietari;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.SubCategoria;

/**
 * @category servei de lloguer
 * 
 * @author degloba
 *
 */
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

	public ILloguerRepository getLloguerRepositoryObjectify() {
		return lloguerRepositoryObjectify;
	}

	public void setLloguerRepositoryObjectify(ILloguerRepository lloguerRepositoryObjectify) {
		this.lloguerRepositoryObjectify = lloguerRepositoryObjectify;
	}


	@Override
	public void creaSubcategoria(SubCategoria subCategoria) {
		// TODO Auto-generated method stub
		
	}



}
