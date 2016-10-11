package com.degloba.organisation.facade;

import java.util.Date;

import com.degloba.organisation.domain.persistence.rdbms.jpa.Post;


/**
 * @author degloba
 * 
 * RDBMS/JPA
 */
public interface OrganisationFacade {

    void createOrganization(OrganizationDto orgToCreate, long parentOrgId, Date date);

    void terminateParty(long partyId, Date date);

    void changeParentOfOrganization(long organizationId, long newParentId, Date date);

    void createPostUnderOrganization(PostDto postDto, long organizationId, Date date);

    PostDto getPost(long postId);
    
    void setPost(Post post);

}
