package com.degloba.rent.cqrs.readmodel.jpa;

import java.util.List;

import com.degloba.rent.domain.persistence.rdbms.jpa.Owner;
import com.degloba.rent.domain.persistence.rdbms.jpa.Product;
import com.degloba.rent.domain.persistence.rdbms.jpa.Subcategory;


public interface IProductFinder {

    List<Product> findProductsByOwner(Owner owner);

    List<Product> findProductBySubcategory(Subcategory subcategory);
}
