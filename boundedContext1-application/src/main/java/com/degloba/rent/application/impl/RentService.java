package com.degloba.rent.application.impl;

import javax.inject.Inject;

import com.degloba.rent.application.IRentService;

import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.IOwnerRepository;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.ISubcategoryRepository;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Owner;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Subcategory;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.jpa.Category;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.jpa.Photo;

import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.jpa.ICategoryRepository;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.jpa.IPhotoRepository;



public class RentService implements IRentService {
	
	@Inject
	private ICategoryRepository categoryRepositoryJpa;
	

	@Inject
	private IPhotoRepository photoRepository;
	
	@Inject
	private IOwnerRepository ownerRepositoryObjectify;
	
	@Inject
	private ISubcategoryRepository subcategoryRepository;

	

	@Override
	public void createCategory(Category category) {
		categoryRepositoryJpa.save(category);
	}
	
/*	Si l'Entitat es Nosql/datastoreGoogle (Api Objectify)
 * @Override
	public void createCategory(Category category) {
		categoryRepositoryObjectify.create(category);
	}*/

	
	public ICategoryRepository getCategoryRepositoryJpa() {
		return categoryRepositoryJpa;
	}

	public void setCategoryRepositoryJpa(ICategoryRepository categoryRepositoryJpa) {
		this.categoryRepositoryJpa = categoryRepositoryJpa;
	}

	@Override
	public void createPhoto(Photo photo) {
		photoRepository.save(photo);
	}

	@Override
	public void createSubcategory(Subcategory subcategory) {
		subcategoryRepository.save(subcategory);

	}

	@Override
	public void createOwner(Owner owner) {
		ownerRepositoryObjectify.create(owner);

	}

}
