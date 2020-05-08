package com.degloba.ecommerce.vendes.domain.specifications;

import javax.persistence.criteria.ListJoin;

import org.springframework.data.jpa.domain.Specification;

import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.User;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.User_;

/**
 * @category Specification compres
 * 
 * @category Utilitza Spring
 * @category Utilitza Java 8 lambda
 *  
 * @see <a href="https://www.logicbig.com/tutorials/spring-framework/spring-data/specifications.html">exemple</a>
 * 
 * @author degloba
 *
 */
public class CompresSpecification {
	
	public static Specification<User> getUsuarisByEmailSpec(String email) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(User_.email), email);
        };
    }

    /*public static Specification<User> getEmployeesByPhoneTypeSpec(PhoneType phoneType) {
        return (root, query, criteriaBuilder) -> {
            ListJoin<Employee, Phone> phoneJoin = root.join(Employee_.phones);
            return criteriaBuilder.equal(phoneJoin.get(Phone_.type), phoneType);
        };
    }*/
}
