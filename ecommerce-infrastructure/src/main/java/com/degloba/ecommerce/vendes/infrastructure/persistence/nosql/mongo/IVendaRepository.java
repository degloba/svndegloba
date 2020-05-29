package com.degloba.ecommerce.vendes.infrastructure.persistence.nosql.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.degloba.ecommerce.vendes.domain.persistence.nosql.mongo.Venda;


public interface IVendaRepository extends MongoRepository<Venda, Long> {

}
