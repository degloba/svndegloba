package com.degloba.hotels.domain.persistence.nosql.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


import com.degloba.hotels.Hotel;


public interface IHotelReactiveRepository extends ReactiveMongoRepository<Hotel, Long> {

}
