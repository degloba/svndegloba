package com.degloba.ecommerce.sales.productscatalog.domain;

import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
public interface IProductRepository {

	public List<Product> findProductWhereBestBeforeExpiredIn(int days);

	public Product load(Class<Product> class1, long productId);
}
