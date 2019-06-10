package com.degloba.rent.facade.jpa;

import com.degloba.lloguer.domain.persistence.nosql.googleDatastore.api.objectify.Foto;

/**
 * @author degloba
 */
public interface PhotoFacade {

    void createPhoto(Foto foto);

}
