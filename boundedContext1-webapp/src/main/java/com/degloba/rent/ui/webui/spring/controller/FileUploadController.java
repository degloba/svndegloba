package com.degloba.rent.ui.webui.spring.controller;

import java.io.IOException;

import java.security.GeneralSecurityException;
import java.security.InvalidParameterException;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;

import org.springframework.stereotype.Component;

import com.degloba.gcs.StorageUtils;
import com.degloba.rent.cqrs.readmodel.CategoryFinder;
import com.degloba.rent.domain.jpa.Category;
import com.degloba.rent.domain.jpa.Owner;
import com.degloba.rent.domain.jpa.Photo;
import com.degloba.rent.domain.jpa.Product;
import com.degloba.rent.domain.jpa.Subcategory;
import com.degloba.rent.facade.CategoryFacade;
import com.degloba.rent.facade.PhotoFacade;
import com.degloba.rent.facade.SubcategoryFacade;


@Component
public class FileUploadController {
	
	private final static Logger logger = Logger.getLogger(FileUploadController.class.getName());
	 
		
    @Inject
    protected PhotoFacade facadePhoto;
    
    @Inject
    protected SubcategoryFacade facadeSubcategory;
    
    @Inject
    protected CategoryFacade facadeCategory;
    
    
    @Inject
    protected CategoryFinder finderCategory;
    
    
    public void handleFileUpload(FileUploadEvent event) 
    			throws IOException, InvalidParameterException {
    	    	
	    String file = event.getFile().getFileName();
	    	    	
			try {
					// Insertem a GCS
					StorageUtils.uploadFile(file, event.getFile().getContentType(), event.getFile().getInputstream(),event.getFile().getSize(), "wwwdegloba-1350.appspot.com");
			    	
	    		    
	    		    // Persistim Photo
	    		    Photo photo = new Photo();
	    		    	    		    
	    		    Owner owner = new Owner();
	    		    Product product = new Product();
	    		    
	    		    //////photo.setCategory(category);
	    		    photo.setProduct(product);
	    		    
	    		    photo.setIdGcs(file);
	    		   /////////// facadePhoto.createPhoto(photo);
	    		    
	    		    
	    		    // Categories/Subcategories
	    		    Category category = new Category();
	    		    category.setDescription("Motor");
	    		    
	    		    Subcategory subcategory = new Subcategory();	    		    	    		           		   
	    		    subcategory.setDescription("Motos");
	    		    subcategory.setCategory(category);
	    		    
	    		    category.getSubcategories().add(subcategory);
	    		    
	    		    Subcategory subcategory2 = new Subcategory();	    		    	    		           		   
	    		    subcategory2.setDescription("Coches");
	    		    subcategory2.setCategory(category);
	    		    
	    		    category.getSubcategories().add(subcategory2);
	    		    
	    		    facadeCategory.createCategory(category);
	    		    
	    		    // Segon
	    		    category = new Category();
	    		    category.setDescription("Deportes");
	    		    	    		    
	    		    subcategory = new Subcategory();	    		    	    		           		   
	    		    subcategory.setDescription("Vela");
	    		    subcategory.setCategory(category);
	    		    
	    		    category.getSubcategories().add(subcategory);
	    		    
	    		    subcategory2 = new Subcategory();	    		    	    		           		   
	    		    subcategory2.setDescription("Futbol");
	    		    subcategory2.setCategory(category);
	    		    category.getSubcategories().add(subcategory2);
	    		    
	    		    facadeCategory.createCategory(category);
	    		    
	    		    
	    		    String a = "1";
				} catch (GeneralSecurityException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
			    
    }

}
