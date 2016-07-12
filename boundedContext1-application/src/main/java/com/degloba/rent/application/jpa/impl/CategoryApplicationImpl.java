package com.degloba.rent.application.impl;

import javax.inject.Inject;

import com.degloba.rent.application.api.CategoryService;
import com.degloba.rent.domain.Category;
import com.degloba.rent.domain.ICategoryRepository;

public class CategoryApplicationImpl implements CategoryService {

	@Inject
	private ICategoryRepository categoryRepository;

	
	public CategoryApplicationImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CategoryApplicationImpl(ICategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}
	
	
	@Override
	public void createCategory(Category category) {
		// TODO Auto-generated method stub
		categoryRepository.save(category);

	}

}
