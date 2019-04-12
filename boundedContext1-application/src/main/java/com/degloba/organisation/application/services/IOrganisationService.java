package com.degloba.organisation.application.services;



import com.degloba.organisation.domain.persistence.rdbms.jpa.Organization;
import com.degloba.organisation.domain.persistence.rdbms.jpa.Party;
import com.degloba.organisation.domain.persistence.rdbms.jpa.Post;
import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;

import java.util.Date;

public interface IOrganisationService {

	<T extends BaseAggregateRoot> T getEntity(Class<T> entityClass, AggregateId entityId);
	 
    void createOrganization(Organization orgToCreate, Organization parent, Date date);

    void terminateParty(Party party, Date date);

    void changeParentOfOrganization(Organization organization, Organization newParent, Date date);

    void createPostUnderOrganization(Post post, Organization organization, Date date);
    
    void cretePost(Post post);
}
