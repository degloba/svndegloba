package com.degloba.ecommerce.sales.domain.persistence.rdbms.jpa;

import java.util.List;

import com.degloba.domain.annotations.DomainRepository;
import com.degloba.domain.persistence.rdbms.jpa.IEntityRepository;
import com.degloba.ecommerce.sales.productscatalog.domain.persistence.rdbms.jpa.Product;


/**
 * 
 * @author degloba
 *
 */
@DomainRepository
public interface ISalesRepository extends IEntityRepository {


	public List<Product> findProductWhereBestBeforeExpiredIn(int days);

	public Product load(Class<Product> class1, long productId);

}
