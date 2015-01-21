package com.degloba.organisation.facade;

import java.util.Date;

/**
 * @author yyang
 */
public interface OrganisationFacade {


    void createOrganization(OrganizationDto orgToCreate, Long parentOrgId, Date date);

    void terminateParty(Long partyId, Date date);

    void changeParentOfOrganization(Long organizationId, Long newParentId, Date date);

    void createPostUnderOrganization(PostDto postDto, Long organizationId, Date date);

    PostDto getPost(long postId);
}