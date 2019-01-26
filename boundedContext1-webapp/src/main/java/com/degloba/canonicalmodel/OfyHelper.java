package com.degloba.canonicalmodel;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Category;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Location;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Owner;

import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Product;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Subcategory;
import com.googlecode.objectify.ObjectifyService;

public class OfyHelper implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// App Engine does not currently invoke this method.
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// This will be invoked as part of a warmup request, or the first user request if no warmup
	    // request.
		//ObjectifyService.init();
	    //ObjectifyService.register(Category.class);
	    //ObjectifyService.register(Subcategory.class);
	    //////////ObjectifyService.register(Photo.class);
	   /* ObjectifyService.register(Location.class);
	    ObjectifyService.register(Product.class);
	    ObjectifyService.register(Owner.class);*/
	}

}
