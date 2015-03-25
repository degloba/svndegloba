package com.degloba.organisation.application;

import com.degloba.organisation.domain.Organization;
import com.degloba.organisation.domain.Party;
import com.degloba.organisation.domain.Post;

import java.util.Date;

public interface OrganisationApplication {

    public <T extends com.degloba.domain.seedwork.Entity> T getEntity(Class<T> entityClass, Long entityId);

    void createOrganization(Organization orgToCreate, Organization parent, Date date);

    void terminateParty(Party party, Date date);

    void changeParentOfOrganization(Organization organization, Organization newParent, Date date);

    void createPostUnderOrganization(Post post, Organization organization, Date date);
}
