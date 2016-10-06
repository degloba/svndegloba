package com.degloba.rent.application.objectify.impl;

import java.io.Serializable;

import javax.inject.Inject;

import com.degloba.rent.application.objectify.api.OwnerService;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.IOwnerRepository;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Owner;



public class OwnerApplicationImpl implements OwnerService, Serializable {

	@Inject
	private IOwnerRepository ownerRepositoryObjectify;

	
	public OwnerApplicationImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OwnerApplicationImpl(IOwnerRepository ownerRepositoryObjectify) {
		super();
		this.ownerRepositoryObjectify = ownerRepositoryObjectify;
	}
	
	
	@Override
	public void createOwner(Owner owner) {
		ownerRepositoryObjectify.create(owner);
	}

	public IOwnerRepository getOwnerRepositoryObjectify() {
		return ownerRepositoryObjectify;
	}

	public void setOwnerRepositoryObjectify(IOwnerRepository ownerRepositoryObjectify) {
		this.ownerRepositoryObjectify = ownerRepositoryObjectify;
	}

	
	
	
}
