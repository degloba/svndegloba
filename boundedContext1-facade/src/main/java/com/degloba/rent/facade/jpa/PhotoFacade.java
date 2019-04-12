package com.degloba.rent.facade.jpa;

import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Photo;

/**
 * @author degloba
 */
public interface PhotoFacade {

    void createPhoto(Photo photo);

}
