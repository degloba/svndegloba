package com.degloba.rent.application.services;

import javax.inject.Inject;


import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Category;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.IRentRepository;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Owner;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Photo;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Subcategory;


public class RentService implements IRentService {
	
/*	@Inject
	private IRentRepository rentRepositoryJpa;*/
	
	
	@Inject
	private IRentRepository rentRepositoryObjectify;



	@Override
	public void createCategory(Category category) {
		rentRepositoryObjectify.create(category);
	}
	
/*	Si l'Entitat es Nosql/datastoreGoogle (Api Objectify)
 * @Override
	public void createCategory(Category category) {
		categoryRepositoryObjectify.create(category);
	}*/

	
/*	public IRentRepository getCategoryRepositoryJpa() {
		return rentRepositoryJpa;
	}

	public void setCategoryRepositoryJpa(IRentRepository rentRepositoryJpa) {
		this.rentRepositoryJpa = rentRepositoryJpa;
	}
*/
	@Override
	public void createPhoto(Photo photo) {
		rentRepositoryObjectify.create(photo);
	}

	@Override
	public void createSubcategory(Subcategory subcategory) {
		rentRepositoryObjectify.save(subcategory);

	}

	@Override
	public void createOwner(Owner owner) {
		rentRepositoryObjectify.create(owner);

	}

}
