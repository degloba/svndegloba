package com.degloba.gcs;

import java.io.File;
import java.io.IOException;

import java.security.GeneralSecurityException;

import java.util.logging.Logger;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;

import com.google.api.client.http.HttpTransport;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.services.storage.Storage;
import com.google.api.services.storage.StorageScopes;


public class StorageFactory {
	  private static Storage instance = null;
	  
	  private final static Logger logger = Logger.getLogger(StorageFactory.class.getName());

	  public static synchronized Storage getService() throws IOException, GeneralSecurityException {
		  
	    if (instance == null) {	        	
	      instance = buildService();
	    }
	    
	    return instance;
	  }

	  private static Storage buildService() throws IOException, GeneralSecurityException {
	    HttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
	    JsonFactory jsonFactory = new JacksonFactory();
	    
	    GoogleCredential credential = new GoogleCredential.Builder().setTransport(transport) 
	             .setJsonFactory(JacksonFactory.getDefaultInstance()) 
	             .setServiceAccountId("246205539021-compute@developer.gserviceaccount.com") 
	             .setServiceAccountScopes(StorageScopes.all()) 	             
	             .setServiceAccountPrivateKeyFromP12File(new File("WEB-INF/wwwdegloba-76423ec780a8.p12")) 
	            .build(); 
	  
	    
	    return new Storage.Builder(transport, jsonFactory, credential)
	        .setApplicationName("wwwdegloba")
	        .build();
	  }
	}
