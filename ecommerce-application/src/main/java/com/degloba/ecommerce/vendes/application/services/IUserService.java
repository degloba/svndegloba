package com.degloba.ecommerce.vendes.application.services;

import java.util.List;

import com.degloba.domain.specifications.SearchCriteria;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.User;

/**
 * @category Servei d'exemple per demostrar que podem utilitzar specifications (degloba i/o spring)
 * 
 * @author degloba
 *
 */
public interface IUserService {
    List<User> searchUser(List<SearchCriteria> params);

    void save(User entity);
}
