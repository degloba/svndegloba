package com.degloba.rent.cqrs.readmodel.impl;

import java.util.List;

// JPA
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

// Spring
import org.springframework.beans.factory.annotation.Qualifier;

// CQRS 
import com.degloba.rent.cqrs.readmodel.CategoryFinder;
import com.degloba.rent.domain.Category;
import com.degloba.cqrs.query.annotations.Finder;


@Finder
public class JpaCategoryFinder implements CategoryFinder {

    @PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactoryDatastore")
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
	@Override
    public List<Category> findCategory() {
        String jpql = "select c from com.degloba.rent.domain.Category c";
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }
}
