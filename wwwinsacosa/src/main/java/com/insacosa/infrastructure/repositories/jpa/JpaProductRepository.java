package com.insacosa.infrastructure.repositories.jpa;

import com.insacosa.domain.Product;
import com.insacosa.domain.ProductRepository;

import ddd.domain.annotations.DomainRepositoryImpl;
import ddd.infrastructure.repo.jpa.GenericJpaRepositoryForBaseEntity;


@DomainRepositoryImpl
public class JpaProductRepository extends GenericJpaRepositoryForBaseEntity<Product> implements ProductRepository {
}
