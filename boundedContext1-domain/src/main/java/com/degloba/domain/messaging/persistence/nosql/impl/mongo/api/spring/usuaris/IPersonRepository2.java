package com.degloba.usuaris.domain.persistence.nosql.mongo.spring;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.degloba.domain.annotations.DomainRepository;
import com.degloba.usuaris.domain.persistence.nosql.mongo.spring.Person;

@DomainRepository
public interface IPersonRepository2 extends MongoRepository<Person, String> {

	Iterable<Person> findByLastname(String lastname);

	List<Person> findAll();

    //Page<Person> findByFirstname(String firstname, Pageable pageable);

    /////Person findByShippingAddresses(Address  address);
	
}
