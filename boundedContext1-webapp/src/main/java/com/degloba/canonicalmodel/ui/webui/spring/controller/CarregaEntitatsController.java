package com.degloba.canonicalmodel.ui.webui.spring.controller;


import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.degloba.domain.persistence.nosql.googleDatastore.api.objectify.DatabaseException;
import com.degloba.domain.persistence.nosql.googleDatastore.api.objectify.IBaseRepository;
import com.degloba.gcm.Topic;

import com.degloba.rent.cqrs.readmodel.jpa.ICategoryFinder;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Category;
import com.degloba.rent.facade.objectify.CategoryFacade;
import com.googlecode.objectify.Key;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author degloba
 *
 */
@Controller
public class CarregaEntitatsController {
	
	private final Logger logger = Logger.getLogger(getClass().getName());
	
	 
    @Inject
    protected CategoryFacade facadeCategoryObjectify;
    	    
    @Inject
    protected ICategoryFinder finderCategory;
    
    @Inject
    protected IBaseRepository categoryRepositoryObjectify;
    

	@RequestMapping(value = "/carregaEntitats")
	public String carregaEntitats(HttpServletRequest request, HttpServletResponse response) {	
		
		//*****************************************************************
	    // Persistencia objectes inicials amb OJECTIFY	   
	    //*****************************************************************
	    
		// 1. CATEGORIA
	    Category category = new Category();
	    category.setDescription("Informatica");
	    
	    // Directament contra el Repository
	    Long categoriaId = categoryRepositoryObjectify.createWithID(category);
	    
	    Key<Category> categoriaKey = categoryRepositoryObjectify.getKey(Category.class,categoriaId);
		    	    	
	    // 2. SUBCATEGORIA
	   /* Subcategory subcategory = new Subcategory();	    		    	    		           		   
	    subcategory.setDescription("Tablet");	   
	    subcategory.setCategory(categoriaKey);
	    
	    Long subcategoriaId = categoryRepositoryObjectify.createWithID(subcategory);
	    Key<Subcategory> subcategoriaKey = categoryRepositoryObjectify.create(categoriaKey, Subcategory.class, subcategoriaId);
	    	   	
	    category.getSubcategories().add(subcategoriaKey);
	    */
	    // Des de UI
	    ///facadeCategoryObjectify.createCategory(category);
	    try {
			categoryRepositoryObjectify.update(Category.class, categoriaId, category);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	           
		return "home";
	}

}
