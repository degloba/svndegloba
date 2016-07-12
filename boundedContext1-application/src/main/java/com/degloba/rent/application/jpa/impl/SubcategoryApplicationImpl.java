package com.degloba.rent.application.jpa.impl;

import javax.inject.Inject;

import com.degloba.rent.application.jpa.api.CategoryService;
import com.degloba.rent.application.jpa.api.SubcategoryService;
import com.degloba.rent.domain.jpa.Category;
import com.degloba.rent.domain.jpa.ICategoryRepository;
import com.degloba.rent.domain.jpa.IPhotoRepository;
import com.degloba.rent.domain.jpa.ISubcategoryRepository;
import com.degloba.rent.domain.jpa.Subcategory;

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
