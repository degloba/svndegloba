package com.degloba.canonicalmodel.ui.webui.spring.controller;


import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.degloba.lloguers.cqrs.readmodel.finders.ILloguerFinder;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Categoria;
import com.degloba.lloguers.facade.objectify.CategoriaFacade;
import com.degloba.persistence.nosql.googleDatastore.api.objectify.DatabaseException;
import com.degloba.persistence.nosql.googleDatastore.api.objectify.IBaseRepository;
import com.googlecode.objectify.Key;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 * @author degloba
 *
 */
@Controller
public class CarregaEntitatsController {
	
	private final Logger logger = Logger.getLogger(getClass().getName());
	
	 
    @Inject
    protected CategoriaFacade facadeCategoryObjectify;
    	    
    @Inject
    protected ILloguerFinder finderCategory;
    
    @Inject
    protected IBaseRepository categoryRepositoryObjectify;
    

	@RequestMapping(value = "/carregaEntitats")
	public String carregaEntitats(HttpServletRequest request, HttpServletResponse response) {	
		
		//*****************************************************************
	    // Persistencia objectes inicials amb OJECTIFY	   
	    //*****************************************************************
	    
		// 1. CATEGORIA
	    Categoria categoria = new Categoria();
	    categoria.setDescripcio("Informatica");
	    
	    // Directament contra el Repository
	    Long categoriaId = categoryRepositoryObjectify.createWithID(categoria);
	    
	    Key<Categoria> categoriaKey = categoryRepositoryObjectify.getKey(Categoria.class,categoriaId);
		    	    	
	    // 2. SUBCATEGORIA
	   /* Subcategory subcategory = new Subcategory();	    		    	    		           		   
	    subcategory.setDescripcio("Tablet");	   
	    subcategory.setCategory(categoriaKey);
	    
	    Long subcategoriaId = categoryRepositoryObjectify.createWithID(subcategory);
	    Key<Subcategory> subcategoriaKey = categoryRepositoryObjectify.create(categoriaKey, Subcategory.class, subcategoriaId);
	    	   	
	    category.getSubcategories().add(subcategoriaKey);
	    */
	    // Des de UI
	    ///facadeCategoryObjectify.createCategory(category);
	    try {
			categoryRepositoryObjectify.update(Categoria.class, categoriaId, categoria);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	           
		return "home";
	}

}
