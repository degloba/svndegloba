package com.degloba.lloguers.ui.web.spring.controller;

import java.io.IOException;

import java.security.GeneralSecurityException;

import java.util.logging.Logger;

import javax.faces.context.FacesContext;
//
import javax.inject.Inject;

// Primefaces
import org.primefaces.event.FileUploadEvent;

// Spring
import org.springframework.stereotype.Component;

import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Foto;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.ILloguerRepository;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Producte;
import com.degloba.lloguers.ui.web.spring.ProductView;
import com.googlecode.objectify.Key;


@Component
public class FileUploadController {
	
	private final static Logger logger = Logger.getLogger(FileUploadController.class.getName());
	 	
    
    @Inject
    protected ILloguerRepository rentRepositoryObjectify;

     /*
      * Manipula l'Event "Upload" de Primefaces  
      */
    public void handleFileUpload(FileUploadEvent event) 
    	{
    	    	
	    	// recuperem el producte (JSF Bean)
	    	FacesContext context = FacesContext.getCurrentInstance();
	    	//ProductView productView = context.getApplication().evaluateExpressionGet(context, "#{productView}", ProductView.class);
	    	
		    String file = event.getFile().getFileName();
		    	    	
		    // 1.- Insertem a GCS (Google Cloud Storage)
			/*try {
					StorageUtils.uploadFile(file, event.getFile().getContentType(), event.getFile().getInputstream(),event.getFile().getSize(), "wwwdegloba-1350.appspot.com");
								
					 // 
					Photo photo = new Photo();
				   	    		    		    
				    // 3.- Recuperem el "Product" (Objectify)
					//Key<Product> product = this.rentRepositoryObjectify.getKey(Product.class, productView.getProducteId());
							    
					//photo.setProduct(product);
					photo.setIdGcs(file);
							  
				    // 2.- Persistim "Photo" (Objectify)
					this.rentRepositoryObjectify.create(photo);
						    
			} catch (IOException | GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
				    
    	}

}
