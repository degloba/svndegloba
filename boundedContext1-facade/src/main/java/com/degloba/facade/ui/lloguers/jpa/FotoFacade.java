package com.degloba.lloguers.facade.jpa;

import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Foto;

/**
 * @author degloba
 */
public interface FotoFacade {

    void createPhoto(Foto foto);

}
