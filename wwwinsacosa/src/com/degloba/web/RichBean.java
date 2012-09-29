package com.degloba.web;

import java.io.Serializable;
import java.util.Date;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class RichBean implements Serializable {

    private static final long serialVersionUID = -2403138958014741653L;
    private String name;

    public RichBean() {
        System.out.println("post construct: initialize....");
        name = "John";
        
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Entity employee = new Entity("Employee");
		employee.setProperty("firstName", "Antonio");
		employee.setProperty("lastName", "Salieri");
		Date hireDate = new Date();
		employee.setProperty("hireDate", hireDate);
		employee.setProperty("attendedHrTraining", true);
		
		datastore.put(employee);
		
		try {
			Entity employeeRe = datastore.get(employee.getKey());
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		Entity address = new Entity("Address", employee.getKey());
		
		
		
		// The Query interface assembles a query
		Query q = new Query("Employee");
		q.addFilter("firstName", Query.FilterOperator.EQUAL, "Antonio");

		
		// PreparedQuery contains the methods for fetching query results
		// from the datastore
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {  
			String firstName = (String) result.getProperty("firstName");  
			String lastName = (String) result.getProperty("lastName");  
			Date hireDatee = (Date) result.getProperty("hireDate");  
			System.out.println(lastName + " " + firstName + ", " + hireDatee.toString() + " inches tall");
		}
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
