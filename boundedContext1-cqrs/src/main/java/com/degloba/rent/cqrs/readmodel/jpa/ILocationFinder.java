package com.degloba.rent.cqrs.readmodel.jpa;


import com.degloba.rent.domain.jpa.Location;
import com.degloba.rent.domain.jpa.Product;


public interface ILocationFinder {

	Location findLocationByProduct(Product product);

}
