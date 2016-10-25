package com.degloba.organisation.application.service;


import com.degloba.domain.persistence.rdbms.jpa.BaseEntity;
import com.degloba.organisation.domain.persistence.rdbms.jpa.Organization;
import com.degloba.organisation.domain.persistence.rdbms.jpa.Party;
import com.degloba.organisation.domain.persistence.rdbms.jpa.Post;


import java.util.Date;

public interface IOrganisationService {

	<T extends BaseEntity> T getEntity(Class<T> entityClass, long entityId);
	 
    void createOrganization(Organization orgToCreate, Organization parent, Date date);

    void terminateParty(Party party, Date date);

    void changeParentOfOrganization(Organization organization, Organization newParent, Date date);

    void createPostUnderOrganization(Post post, Organization organization, Date date);
    
    void cretePost(Post post);
}
