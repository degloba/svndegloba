package com.degloba.rent.application.objectify.impl;

import javax.inject.Inject;

import com.degloba.rent.application.objectify.api.SubcategoryService;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.ISubcategoryRepository;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Subcategory;


public class SubcategoryApplicationImpl implements SubcategoryService {

	@Inject
	private ISubcategoryRepository subcategoryRepository;
	
	public SubcategoryApplicationImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SubcategoryApplicationImpl(ISubcategoryRepository subcategoryRepository) {
		super();
		this.subcategoryRepository = subcategoryRepository;
	}
	
	
	@Override
	public void createSubcategory(Subcategory subcategory) {
		// TODO Auto-generated method stub
		subcategoryRepository.save(subcategory);

	}

}
