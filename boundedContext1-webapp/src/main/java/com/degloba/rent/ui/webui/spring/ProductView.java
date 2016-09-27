package com.degloba.rent.ui.webui.spring;


import java.io.Serializable;

import javax.annotation.PostConstruct;

// JSF
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

// 
import javax.inject.Inject;

// Spring
import org.springframework.stereotype.Component;

// Spring-Webflow
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.mvc.servlet.MvcExternalContext;

// Objectify
import com.degloba.objectify.DatabaseException;
import com.degloba.objectify.IBaseRepositoryObjectify;

// Entitats/Objectify
import com.degloba.rent.domain.objectify.Owner;

import com.degloba.rent.facade.objectify.OwnerFacade;

//
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

// Validation
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;


/**
 * @author degloba
 *
 */
@Component
@ManagedBean
@ViewScoped
public class ProductView implements Serializable{
	
	//@Size(min=2,max=5)  NO FUNCIONA.Mirar http://stackoverflow.com/questions/19216495/app-engine-jar-in-web-inf-lib-but-still-getting-java-lang-classnotfoundexceptio
	String name;
	
	String description;
	
	@Digits(integer=3,fraction=2)
	private Double price;
	
    @Inject
    protected OwnerFacade ownerPhoto;

    
    @Inject
    protected IBaseRepositoryObjectify ownerRepositoryObjectify;
	
    @PostConstruct
    public void init() {
				   
    }
	
	 public void onAddProduct(RequestContext context) throws DatabaseException {
		 
		 MvcExternalContext externalContext =
				  (MvcExternalContext)context.getExternalContext();
		 HttpServletRequest request = (HttpServletRequest) externalContext.getNativeRequest();
								
			    Cookie[] cookies = request.getCookies();
	
			    for (Cookie cookie : cookies) {
			      if ("uid".equals(cookie.getName())) {
			    	  String uid = cookie.getValue();
			    	  
			    	 Owner o = ownerRepositoryObjectify.getById(Owner.class, uid);
			    	  
			    	 if (o == null) {
			    		 Owner owner = new Owner();
				    	  
				    	  owner.setId(uid);
				    	  ownerRepositoryObjectify.create(owner); 
			    	 }
			    	  
			      }
			    }
	    }
	 
	 
	
	 
	 // getters - setters
	 
	 	public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

}
