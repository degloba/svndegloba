package com.insacosa.domain;

import ddd.domain.annotations.DomainRepository;

/**
 * 
 * @author Slawek
 *
 */
@DomainRepository
public interface ProductRepository {

	public Product load(Long id);
}
