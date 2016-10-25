package com.degloba.rent.application.service;

import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Category;
//Entitats NoSql/GoogleDatastore (Api Objectify)
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Owner;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Photo;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Subcategory;


//Entitats NoSql/GoogleDatastore (Api JPA)
/*import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.jpa.Category;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.jpa.Photo*/


//Entitats Rdbms (Api JPA)
/*import com.degloba.rent.domain.persistence.rdbms.jpa.Category;
import com.degloba.rent.domain.persistence.rdbms.jpa.Photo;*/


//Entitats NoSql/MongoDB (Api xxx)
//...............

public interface IRentService {

	void createCategory(Category category);
	
    void createPhoto(Photo photo);
    
    void createSubcategory(Subcategory subcategory);
    
    void createOwner(Owner owner);
}
