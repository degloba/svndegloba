package com.degloba.usuaris.domain.persistence.nosql.mongo.spring;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


import com.degloba.domain.annotations.DomainRepository;
import com.degloba.usuaris.domain.persistence.nosql.mongo.spring.Person;

@DomainRepository
public interface IPersonRepository2 extends MongoRepository<Person, String> {

	Iterable<Person> findByLastname(String lastname);

    //Page<Person> findByFirstname(String firstname, Pageable pageable);

    /////Person findByShippingAddresses(Address  address);
	
}
