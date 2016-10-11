package com.degloba.ecommerce.sales.application.api.service;

import com.google.appengine.api.datastore.Key;


/**
 * Shopping Cart is just an Application Feature, with no Domain Model
 * 
 */
public interface IShoppingCart {
	public void add(Key productId);
}
