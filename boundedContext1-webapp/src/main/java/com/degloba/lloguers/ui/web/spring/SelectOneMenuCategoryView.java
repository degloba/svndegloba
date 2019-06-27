package com.degloba.lloguers.ui.web.spring;


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

import com.degloba.lloguers.cqrs.readmodel.finders.ILloguerFinder;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Categoria;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.SubCategoria;


@Component
@ManagedBean
@ViewScoped
public class SelectOneMenuCategoryView {

	private Map<String,List<SubCategoria>> data = new HashMap<String, List<SubCategoria>>();
	
	private String category; 
    private String subcategory; 
    private Map<String,String> categories;
    private List<SubCategoria> subCategorias;
    
    @Inject
    private ILloguerFinder categoryFinder;
    
    	
	@PostConstruct
    public void init() {
                 		
		//categories
		categories = new HashMap<String, String>();
		
		/*for (Category c : categoryFinder.findCategories()) {
			categories.put(c.getDescripcio(), c.getDescripcio());
			
			//subcategories = subcategoryFinder.findSubcategoriesByCategory(c);
			
			data.put(c.getDescripcio(),subcategories );
			
		  }*/
		   
    }
	
	 public void onCategoryChange() {
	        if(category !=null && !category.equals(""))
	        	subCategorias = data.get(category);
	        else
	        	subCategorias = new ArrayList<SubCategoria>();
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

	
	public Map<String, List<SubCategoria>> getData() {
		return data;
	}

	public void setData(Map<String, List<SubCategoria>> data) {
		this.data = data;
	}

	

	public List<SubCategoria> getSubcategories() {
		return subCategorias;
	}

	public void setSubcategories(List<SubCategoria> subCategorias) {
		this.subCategorias = subCategorias;
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
