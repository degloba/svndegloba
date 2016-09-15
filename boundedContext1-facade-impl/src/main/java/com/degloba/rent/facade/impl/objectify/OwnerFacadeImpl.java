package com.degloba.rent.facade.impl.objectify;

import java.io.Serializable;

import javax.inject.Inject;

import com.degloba.rent.application.objectify.api.OwnerService;
import com.degloba.rent.domain.objectify.Owner;
import com.degloba.rent.facade.objectify.CategoryFacade;
import com.degloba.rent.facade.objectify.OwnerFacade;


public class OwnerFacadeImpl implements OwnerFacade, Serializable {

    @Inject
    protected OwnerService ownerApplicationObjectify;

    public OwnerFacadeImpl(OwnerService application) {
        this.ownerApplicationObjectify = application;
    }
   
	public OwnerFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createOwner(Owner owner) {
		// TODO Auto-generated method stub
		ownerApplicationObjectify.createOwner(owner);
	}

	public OwnerService getOwnerApplicationObjectify() {
		return ownerApplicationObjectify;
	}

	public void setOwnerApplicationObjectify(OwnerService ownerApplicationObjectify) {
		this.ownerApplicationObjectify = ownerApplicationObjectify;
	}

	
	
		
}
