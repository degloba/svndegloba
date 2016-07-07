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
import com.degloba.rent.domain.Category;

@Component
@ManagedBean
@SessionScoped
public class SelectOneMenuCategoryView {

	private Map<String,String> categories = new HashMap<String, String>();
	   
    
    @Inject
    private CategoryFinder categoryFinder;
    	
	@PostConstruct
    public void init() {
                 
		//categories
        categories = new HashMap<String, String>();
		
		for (Category c : categoryFinder.findCategories()) {
			categories.put(c.getDescription(), c.getDescription());
		  }
		   
    }

	public Map<String, String> getCategories() {
		return categories;
	}

	public void setCategories(Map<String, String> categories) {
		this.categories = categories;
	}

}
