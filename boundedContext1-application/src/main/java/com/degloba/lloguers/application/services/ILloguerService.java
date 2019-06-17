package com.degloba.lloguers.application.services;

import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Category;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Foto;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Propietari;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Subcategory;


//Entitats NoSql/GoogleDatastore (Api JPA)
/*import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.jpa.Category;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.jpa.Photo*/


//Entitats Rdbms (Api JPA)
/*import com.degloba.rent.domain.persistence.rdbms.jpa.Category;
import com.degloba.rent.domain.persistence.rdbms.jpa.Photo;*/


//Entitats NoSql/MongoDB (Api xxx)
//...............

public interface ILloguerService {

	void createCategory(Category category);
	
    void createPhoto(Foto foto);
    
    void createSubcategory(Subcategory subcategory);
    
    void createOwner(Propietari propietari);
}
