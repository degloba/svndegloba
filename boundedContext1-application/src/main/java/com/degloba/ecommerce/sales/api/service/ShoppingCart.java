package com.degloba.ecommerce.sales.api.service;

import com.google.appengine.api.datastore.Key;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;

/**
 * Shopping Cart is just an Application Feature, with no Domain Model
 * 
 */
public interface ShoppingCart {
	public void add(Key productId);
}
