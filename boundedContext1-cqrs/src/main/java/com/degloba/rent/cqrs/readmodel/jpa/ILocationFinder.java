package com.degloba.rent.cqrs.readmodel.jpa;

import com.degloba.rent.domain.persistence.rdbms.jpa.Location;
import com.degloba.rent.domain.persistence.rdbms.jpa.Product;

public interface ILocationFinder {

	Location findLocationByProduct(Product product);

}
