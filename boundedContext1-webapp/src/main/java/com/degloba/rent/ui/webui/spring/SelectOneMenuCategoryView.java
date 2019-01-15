package com.degloba.rent.ui.webui.spring;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.degloba.rent.cqrs.readmodel.finders.IRentFinder;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Category;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Subcategory;


@Component
@ManagedBean
@ViewScoped
public class SelectOneMenuCategoryView {

	private Map<String,List<Subcategory>> data = new HashMap<String, List<Subcategory>>();
	
	private String category; 
    private String subcategory; 
    private Map<String,String> categories;
    private List<Subcategory> subcategories;
    
    @Inject
    private IRentFinder categoryFinder;
    
    	
	@PostConstruct
    public void init() {
                 		
		//categories
		categories = new HashMap<String, String>();
		
		/*for (Category c : categoryFinder.findCategories()) {
			categories.put(c.getDescription(), c.getDescription());
			
			//subcategories = subcategoryFinder.findSubcategoriesByCategory(c);
			
			data.put(c.getDescription(),subcategories );
			
		  }*/
		   
    }
	
	 public void onCategoryChange() {
	        if(category !=null && !category.equals(""))
	        	subcategories = data.get(category);
	        else
	        	subcategories = new ArrayList<Subcategory>();
	    }

	 public void displayLocation() {
	        FacesMessage msg;
	        if(subcategory != null && category != null)
	            msg = new FacesMessage("Selected", subcategory + " of " + category);
	        else
	            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "Subcategory is not selected."); 
	             
	        FacesContext.getCurrentInstance().addMessage(null, msg);  
	    }
	 
	 // getters - setters
	 
	 
	 
	public Map<String, String> getCategories() {
		return categories;
	}

	
	public Map<String, List<Subcategory>> getData() {
		return data;
	}

	public void setData(Map<String, List<Subcategory>> data) {
		this.data = data;
	}

	

	public List<Subcategory> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public void setCategories(Map<String, String> categories) {
		this.categories = categories;
	}

}
