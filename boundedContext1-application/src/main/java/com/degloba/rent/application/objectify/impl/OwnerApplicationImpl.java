package com.degloba.rent.application.objectify.impl;

import java.io.Serializable;

import javax.inject.Inject;

import com.degloba.rent.application.objectify.api.OwnerService;
import com.degloba.rent.domain.objectify.IOwnerRepositoryObjectify;
import com.degloba.rent.domain.objectify.Owner;


public class OwnerApplicationImpl implements OwnerService, Serializable {

	@Inject
	private IOwnerRepositoryObjectify ownerRepositoryObjectify;

	
	public OwnerApplicationImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OwnerApplicationImpl(IOwnerRepositoryObjectify ownerRepositoryObjectify) {
		super();
		this.ownerRepositoryObjectify = ownerRepositoryObjectify;
	}
	
	
	@Override
	public void createOwner(Owner owner) {
		ownerRepositoryObjectify.create(owner);
	}

	public IOwnerRepositoryObjectify getOwnerRepositoryObjectify() {
		return ownerRepositoryObjectify;
	}

	public void setOwnerRepositoryObjectify(IOwnerRepositoryObjectify ownerRepositoryObjectify) {
		this.ownerRepositoryObjectify = ownerRepositoryObjectify;
	}

	
	
	
}
