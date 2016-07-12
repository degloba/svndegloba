package com.degloba.rent.cqrs.readmodel;

import java.util.List;

import com.degloba.rent.domain.jpa.Owner;
import com.degloba.rent.domain.jpa.Product;
import com.degloba.rent.domain.jpa.Subcategory;

public interface ProductFinder {

    List<Product> findProductsByOwner(Owner owner);

    List<Product> findProductBySubcategory(Subcategory subcategory);
}
