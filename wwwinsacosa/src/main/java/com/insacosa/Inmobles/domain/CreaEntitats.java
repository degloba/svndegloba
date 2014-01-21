package com.insacosa.Inmobles.domain;

import java.io.IOException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class CreaEntitats {
    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Entity usuari = new Entity("Usuari");
		
		usuari.setProperty("nomusuari", "pere");
		
		usuari.setProperty("password", "a");
		
		
		datastore.put(usuari);
		
	}
	
	
	
}
