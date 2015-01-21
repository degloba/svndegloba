package com.degloba.organisation.application;

import java.util.Date;

import com.degloba.accountability.Organization;
import com.degloba.accountability.Party;
import com.degloba.accountability.Post;

import domain.seedwork.Entity;

public interface OrganisationApplication {

    public <T extends Entity> T getEntity(Class<T> entityClass, Long entityId);

    void createOrganization(Organization orgToCreate, Organization parent, Date date);

    void terminateParty(Party party, Date date);

    void changeParentOfOrganization(Organization organization, Organization newParent, Date date);

    void createPostUnderOrganization(Post post, Organization organization, Date date);
}
