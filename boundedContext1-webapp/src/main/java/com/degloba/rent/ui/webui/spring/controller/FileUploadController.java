package com.degloba.rent.ui.webui.spring.controller;

import java.io.IOException;

import java.security.GeneralSecurityException;
import java.security.InvalidParameterException;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
//
import javax.inject.Inject;

// Primefaces
import org.primefaces.event.FileUploadEvent;

// Spring
import org.springframework.stereotype.Component;

// Google Cloud Storage/degloba
import com.degloba.gcs.StorageUtils;
import com.degloba.objectify.IBaseRepositoryObjectify;


// Entitats/Objectify
import com.degloba.rent.domain.objectify.Photo;
import com.degloba.rent.domain.objectify.Product;


import com.degloba.rent.facade.jpa.PhotoFacade;
import com.degloba.rent.ui.webui.spring.ProductView;
import com.googlecode.objectify.Key;


@Component
public class FileUploadController {
	
	private final static Logger logger = Logger.getLogger(FileUploadController.class.getName());
	 	
		
    @Inject
    protected PhotoFacade facadePhoto;
    
    @Inject
    protected IBaseRepositoryObjectify ownerRepositoryObjectify;

     /*
      * Manipula l'Event "Upload" de Primefaces  
      */
    public void handleFileUpload(FileUploadEvent event) 
    	{
    	    	
	    	// recuperem el producte (JSF Bean)
	    	FacesContext context = FacesContext.getCurrentInstance();
	    	ProductView productView = context.getApplication().evaluateExpressionGet(context, "#{productView}", ProductView.class);
	    	
		    String file = event.getFile().getFileName();
		    	    	
		    // 1.- Insertem a GCS (Google Cloud Storage)
			try {
					StorageUtils.uploadFile(file, event.getFile().getContentType(), event.getFile().getInputstream(),event.getFile().getSize(), "wwwdegloba-1350.appspot.com");
								
					 // 
					Photo photo = new Photo();
				   	    		    		    
				    // 3.- Recuperem el "Product" (Objectify)
					Key<Product> product = this.ownerRepositoryObjectify.getKey(Product.class, productView.getProductId());
							    
					photo.setProduct(product);
					photo.setIdGcs(file);
							  
				    // 2.- Persistim "Photo" (Objectify)
					this.ownerRepositoryObjectify.create(photo);
						    
			} catch (IOException | GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				    
    	}

}
