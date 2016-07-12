package com.degloba.rent.cqrs.readmodel;


import com.degloba.rent.domain.jpa.Location;
import com.degloba.rent.domain.jpa.Product;


public interface LocationFinder {

	Location findLocationByProduct(Product product);

}
