package com.degloba.rent.ui.webui.spring;


import java.util.HashMap;

import java.util.Map;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.springframework.stereotype.Component;


@Component
@ManagedBean
@SessionScoped
public class SelectOneMenuSubcategoryView {

	private Map<String,String> subcategories = new HashMap<String, String>();
	       
    	
	@PostConstruct
    public void init() {
                 
		//categories
		subcategories = new HashMap<String, String>();
		
		/*
		for (Subcategory s : subcategoryFinder.findSubcategoriesByCategory(category)) {
			subcategories.put(s.getDescription(), s.getDescription());
		  }*/
		   
    }

	public Map<String, String> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(Map<String, String> subcategories) {
		this.subcategories = subcategories;
	}



}
