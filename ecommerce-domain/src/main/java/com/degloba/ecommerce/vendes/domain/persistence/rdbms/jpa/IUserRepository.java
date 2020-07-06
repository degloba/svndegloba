package com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



/**
 * https://github.com/eugenp/tutorials/blob/master/spring-rest-query-language/pom.xml
 * https://www.baeldung.com/spring-data-jpa-multiple-databases
 * 
 * @author degloba
 *
 */
public interface IUserRepository 
		extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

}
