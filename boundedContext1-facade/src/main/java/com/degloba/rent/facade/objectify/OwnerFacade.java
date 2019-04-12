package com.degloba.rent.facade.objectify;

import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Owner;

/**
 * @author degloba
 */
public interface OwnerFacade {

    void createOwner(Owner owner);

}
