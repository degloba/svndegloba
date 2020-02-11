package com.degloba.organisation.application.services;



import com.degloba.organitzacio.domain.persistence.rdbms.jpa.Organitzacio;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.Party;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.Post;
import com.degloba.organitzacio.facade.dtos.PostDto;
import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;

import java.util.Date;

/**
 * 
 * @author degloba
 * 
 * @category Servei Organitzacio
 */
public interface IOrganitzacioService {

	<T extends BaseAggregateRoot> T getEntity(Class<T> entityClass, AggregateId entityId);
	 
    void creaOrganitzacio(Organitzacio orgToCreate, Organitzacio parent, Date date);

    void terminateParty(Party party, Date date);

    void changeParentOfOrganization(Organitzacio organitzacio, Organitzacio newParent, Date date);

    void createPostUnderOrganization(Post post, Organitzacio organitzacio, Date date);
    
    void cretePost(PostDto post);
}
