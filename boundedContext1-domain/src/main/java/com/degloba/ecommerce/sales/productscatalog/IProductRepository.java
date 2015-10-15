package com.degloba.ecommerce.sales.productscatalog;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.Key;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;

@Repository
public interface IProductRepository {

	public Product load(Key productId);
	
	public List<Product> findProductWhereBestBeforeExpiredIn(int days);

	public Product load(Class<Product> class1, Key productId);
}
