package com.degloba.rent.facade.impl.objectify;

import java.io.Serializable;

import javax.inject.Inject;

import com.degloba.lloguers.application.services.ILloguerService;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Propietari;
import com.degloba.lloguers.facade.objectify.CategoriaFacade;
import com.degloba.lloguers.facade.objectify.PropietariFacade;

/**
 * @category
 * 
 * @author degloba
 *
 */
public class PropietariFacadeImpl implements PropietariFacade, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    protected ILloguerService propietariApplicationObjectify;

    public PropietariFacadeImpl(ILloguerService application) {
        this.propietariApplicationObjectify = application;
    }
   
	public PropietariFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void creaPropietari(Propietari propietari) {
		// TODO Auto-generated method stub
		propietariApplicationObjectify.creaPropietari(propietari);
	}

	public ILloguerService getPropietariApplicationObjectify() {
		return propietariApplicationObjectify;
	}

	public void setOwnerApplicationObjectify(ILloguerService propietariApplicationObjectify) {
		this.propietariApplicationObjectify = propietariApplicationObjectify;
	}

	
	
		
}
