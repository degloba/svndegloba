package com.degloba.organisation.facade;

import java.util.Date;

import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 */
public interface OrganisationFacade {

    void createOrganization(OrganizationDto orgToCreate, Key parentOrgId, Date date);

    void terminateParty(Key partyId, Date date);

    void changeParentOfOrganization(Key organizationId, Key newParentId, Date date);

    void createPostUnderOrganization(PostDto postDto, Key organizationId, Date date);

    PostDto getPost(Key postId);

}
