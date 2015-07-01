package com.degloba.organisation.application.api;

import com.degloba.domain.Entity;
import com.degloba.organisation.domain.Organization;
import com.degloba.organisation.domain.Party;
import com.degloba.organisation.domain.Post;
import com.google.appengine.api.datastore.Key;

import java.util.Date;

public interface OrganisationService {

	<T extends Entity> T getEntity(Class<T> entityClass, Key entityId);
	 
    void createOrganization(Organization orgToCreate, Organization parent, Date date);

    void terminateParty(Party party, Date date);

    void changeParentOfOrganization(Organization organization, Organization newParent, Date date);

    void createPostUnderOrganization(Post post, Organization organization, Date date);
}
