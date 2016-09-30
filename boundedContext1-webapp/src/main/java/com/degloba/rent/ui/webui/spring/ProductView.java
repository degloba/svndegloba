package com.degloba.rent.ui.webui.spring;


import java.io.IOException;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.security.InvalidParameterException;

import javax.annotation.PostConstruct;

// JSF
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

// 
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;
import org.springframework.data.repository.query.QueryLookupStrategy.Key;
// Spring
import org.springframework.stereotype.Component;

// Spring-Webflow
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.mvc.servlet.MvcExternalContext;

import com.degloba.domain.persistence.nosql.googleDatastore.api.objectify.DatabaseException;
import com.degloba.domain.persistence.nosql.googleDatastore.api.objectify.IBaseRepository;
import com.degloba.gcs.StorageUtils;
// Objectify

import com.degloba.rent.domain.jpa.Photo;
// Entitats/Objectify
import com.degloba.rent.domain.objectify.Owner;
import com.degloba.rent.domain.objectify.Product;
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
@SessionScoped
public class ProductView implements Serializable{
	
	private Long productId; 
	
	//@Size(min=2,max=5)  NO FUNCIONA.Mirar http://stackoverflow.com/questions/19216495/app-engine-jar-in-web-inf-lib-but-still-getting-java-lang-classnotfoundexceptio
	String name;
	
	String description;
	
		
	@Digits(integer=3,fraction=2)
	private Double price;
	
    
    @Inject
    protected IBaseRepository ownerRepositoryObjectify;
    
 	
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
			    	  
			    	 // Comprovem si tenim l'owner al datastore
			    	 // si no, el creem
			    	 String ownerId = "";
			    	 if (o == null) {
			    		 Owner owner = new Owner();
				    	  
				    	  owner.setId(uid);
				    	  ownerId = ownerRepositoryObjectify.createWithKey(owner);
			    	 }				    	  
			    	 else {
				    	  
				    //ownerId = ownerRepositoryObjectify.geto.getId();  
			    	 }
			    	 
			    	 // Producte
			    	 Product product = new Product();
			    	 product.setDescription(this.description);
			    	 product.setPrice(price);
			    	 			    	
			    	 product.setOwner(com.googlecode.objectify.Key.create(Owner.class, uid));
			    	 
			    	 // creem el producte i guardem el seu Id 
			    	 productId = this.ownerRepositoryObjectify.createWithID(product);
			    	  
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

		public Long getProductId() {
			return productId;
		}

		public void setProductId(Long productId) {
			this.productId = productId;
		}

		

}
