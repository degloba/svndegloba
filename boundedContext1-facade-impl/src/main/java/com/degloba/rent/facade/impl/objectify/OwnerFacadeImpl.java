package com.degloba.rent.facade.impl.objectify;

import java.io.Serializable;

import javax.inject.Inject;

import com.degloba.lloguer.domain.persistence.nosql.googleDatastore.api.objectify.Propietari;
import com.degloba.rent.application.services.IRentService;
import com.degloba.rent.facade.objectify.CategoryFacade;
import com.degloba.rent.facade.objectify.OwnerFacade;


public class OwnerFacadeImpl implements OwnerFacade, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    protected IRentService ownerApplicationObjectify;

    public OwnerFacadeImpl(IRentService application) {
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

	public IRentService getOwnerApplicationObjectify() {
		return ownerApplicationObjectify;
	}

	public void setOwnerApplicationObjectify(IRentService ownerApplicationObjectify) {
		this.ownerApplicationObjectify = ownerApplicationObjectify;
	}

	
	
		
}
