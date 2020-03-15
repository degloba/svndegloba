package com.degloba.messaging.domain.persistence.nosql.impl.mongo.api.spring.usuaris;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.degloba.domain.annotations.DomainRepository;
import com.degloba.messaging.domain.persistence.nosql.impl.mongo.api.spring.usuaris.Person;

@DomainRepository
public interface IPersonRepository2 extends MongoRepository<Person, String> {

	Iterable<Person> findByLastname(String lastname);

	List<Person> findAll();

    //Page<Person> findByFirstname(String firstname, Pageable pageable);

    /////Person findByShippingAddresses(Address  address);
	
}
