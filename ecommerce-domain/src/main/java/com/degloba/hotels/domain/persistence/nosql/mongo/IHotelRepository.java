package com.degloba.hotels.domain.persistence.nosql.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.degloba.ecommerce.vendes.domain.persistence.nosql.mongo.Venda;


public interface IHotelRepository extends MongoRepository<Venda, Long> {

}
