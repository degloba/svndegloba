package com.degloba.lloguers.facade.impl;

import java.io.Serializable;

import javax.inject.Inject;

import com.degloba.lloguers.application.services.ILloguerService;
import com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify.Propietari;
import com.degloba.lloguers.facade.ui.ICategoriaFacade;
import com.degloba.lloguers.facade.ui.IPropietariFacade;

import lombok.Value;

/**
 * @category
 * 
 * @author degloba
 *
 */
@Value
public class PropietariFacadeImpl implements IPropietariFacade, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
    protected ILloguerService propietariApplicationObjectify;

   
	@Override
	public void creaPropietari(Propietari propietari) {
		// TODO Auto-generated method stub
		propietariApplicationObjectify.creaPropietari(propietari);
	}
		
}
