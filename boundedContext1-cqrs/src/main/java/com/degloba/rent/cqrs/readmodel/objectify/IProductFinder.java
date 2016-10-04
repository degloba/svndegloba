package com.degloba.rent.cqrs.readmodel.objectify;

import java.util.List;

import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Owner;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Product;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Subcategory;



public interface IProductFinder {

    List<Product> findProductsByOwner(Owner owner);

    List<Product> findProductBySubcategory(Subcategory subcategory);
}
