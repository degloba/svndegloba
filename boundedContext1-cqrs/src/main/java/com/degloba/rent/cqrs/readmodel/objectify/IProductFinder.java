package com.degloba.rent.cqrs.readmodel.objectify;

import java.util.List;

import com.degloba.rent.domain.objectify.Owner;
import com.degloba.rent.domain.objectify.Product;
import com.degloba.rent.domain.objectify.Subcategory;


public interface IProductFinder {

    List<Product> findProductsByOwner(Owner owner);

    List<Product> findProductBySubcategory(Subcategory subcategory);
}
