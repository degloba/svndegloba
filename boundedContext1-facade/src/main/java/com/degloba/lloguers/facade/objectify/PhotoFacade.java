package com.degloba.lloguers.facade.objectify;

import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Foto;

/**
 * @author degloba
 */
public interface PhotoFacade {

    void createPhoto(Foto foto);

}
