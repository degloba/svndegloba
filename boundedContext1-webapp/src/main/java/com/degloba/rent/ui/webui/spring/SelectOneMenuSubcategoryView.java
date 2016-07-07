package com.degloba.rent.ui.webui.spring;


import java.util.HashMap;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.degloba.ecommerce.shipping.cqrs.readmodel.ShipmentFinder;
import com.degloba.rent.cqrs.readmodel.CategoryFinder;
import com.degloba.rent.cqrs.readmodel.SubcategoryFinder;
import com.degloba.rent.domain.Category;
import com.degloba.rent.domain.Subcategory;

@Component
@ManagedBean
@SessionScoped
public class SelectOneMenuSubcategoryView {

	private Map<String,String> subcategories = new HashMap<String, String>();
	   
    
    @Inject
    private SubcategoryFinder subcategoryFinder;
    	
	@PostConstruct
    public void init() {
                 
		//categories
		subcategories = new HashMap<String, String>();
		
		for (Subcategory s : subcategoryFinder.findSubcategoriesByCategory(category)) {
			subcategories.put(s.getDescription(), s.getDescription());
		  }
		   
    }

	public Map<String, String> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(Map<String, String> subcategories) {
		this.subcategories = subcategories;
	}

	public SubcategoryFinder getSubcategoryFinder() {
		return subcategoryFinder;
	}

	public void setSubcategoryFinder(SubcategoryFinder subcategoryFinder) {
		this.subcategoryFinder = subcategoryFinder;
	}



}
