package com.degloba.rent.facade.objectify;

import com.degloba.lloguer.domain.persistence.nosql.googleDatastore.api.objectify.Propietari;

/**
 * @author degloba
 */
public interface OwnerFacade {

    void createOwner(Propietari propietari);

}
