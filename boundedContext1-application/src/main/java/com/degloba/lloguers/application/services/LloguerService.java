package com.degloba.lloguers.application.services;

import javax.inject.Inject;

import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Categoria;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Foto;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.ILloguerRepository;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Propietari;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.SubCategoria;


public class LloguerService implements ILloguerService {
	
/*	@Inject
	private IRentRepository rentRepositoryJpa;*/
	
	
	@Inject
	private ILloguerRepository rentRepositoryObjectify;



	@Override
	public void creaCategoria(Categoria categoria) {
		rentRepositoryObjectify.create(categoria);
	}
	
	
	@Override
	public void creaFoto(Foto foto) {
		rentRepositoryObjectify.create(foto);
	}

	
	@Override
	public void creaPropietari(Propietari propietari) {
		rentRepositoryObjectify.create(propietari);

	}

	public ILloguerRepository getRentRepositoryObjectify() {
		return rentRepositoryObjectify;
	}

	public void setRentRepositoryObjectify(ILloguerRepository rentRepositoryObjectify) {
		this.rentRepositoryObjectify = rentRepositoryObjectify;
	}

	@Override
	public void creaSubCategoria(SubCategoria subCategoria) {
		// TODO Auto-generated method stub
		
	}
	
	

}
