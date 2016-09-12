package com.degloba.rent.ui.webui.spring;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.mvc.servlet.MvcExternalContext;

import com.degloba.objectify.GenericDao;
import com.degloba.rent.domain.objectify.Owner;
import com.degloba.rent.facade.jpa.PhotoFacade;
import com.degloba.rent.facade.objectify.OwnerFacade;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author pere
 *
 */
@Component
@ManagedBean
@ViewScoped
public class RentYourStuffView implements Serializable{
	
	
	String description;
	Long price;
	
    @Inject
    protected OwnerFacade ownerPhoto;

    
    @Inject
    protected GenericDao ownerRepositoryObjectify;
	
    @PostConstruct
    public void init() {
				   
    }
	
	 public void onAddProduct(RequestContext context) {
		 
		 MvcExternalContext externalContext =
				  (MvcExternalContext)context.getExternalContext();
		 HttpServletRequest request = (HttpServletRequest) externalContext.getNativeRequest();
								
			    Cookie[] cookies = request.getCookies();
	
			    for (Cookie cookie : cookies) {
			      if ("uid".equals(cookie.getName())) {
			    	  String uid = cookie.getValue();
			    	  
			    	  Owner owner = new Owner();
			    	  
			    	  owner.setId(uid);
			    	  ownerRepositoryObjectify.create(owner);
			      }
			    }
	    }
	 
	 
	 public void displayLocation() {
	        FacesMessage msg;
	       
	    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
	 
	 // getters - setters
	 

}
