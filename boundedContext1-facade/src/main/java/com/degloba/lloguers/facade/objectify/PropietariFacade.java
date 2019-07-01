package com.degloba.lloguers.facade.objectify;

import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Propietari;

/**
 * @author degloba
 */
public interface OwnerFacade {

    void createOwner(Propietari propietari);

}
