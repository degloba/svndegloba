package com.degloba.rent.facade.impl.objectify;

import java.io.Serializable;

import javax.inject.Inject;

import com.degloba.lloguers.application.services.ILloguerService;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Propietari;
import com.degloba.lloguers.facade.objectify.CategoryFacade;
import com.degloba.lloguers.facade.objectify.OwnerFacade;


public class OwnerFacadeImpl implements OwnerFacade, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    protected ILloguerService ownerApplicationObjectify;

    public OwnerFacadeImpl(ILloguerService application) {
        this.ownerApplicationObjectify = application;
    }
   
	public OwnerFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createOwner(Propietari propietari) {
		// TODO Auto-generated method stub
		ownerApplicationObjectify.createOwner(propietari);
	}

	public ILloguerService getOwnerApplicationObjectify() {
		return ownerApplicationObjectify;
	}

	public void setOwnerApplicationObjectify(ILloguerService ownerApplicationObjectify) {
		this.ownerApplicationObjectify = ownerApplicationObjectify;
	}

	
	
		
}
