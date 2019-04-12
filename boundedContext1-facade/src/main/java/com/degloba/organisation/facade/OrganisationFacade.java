package com.degloba.organisation.facade;

import java.util.Date;

import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
import com.degloba.organisation.domain.persistence.rdbms.jpa.Post;


/**
 * @author degloba
 * 
 * RDBMS/JPA
 */
public interface OrganisationFacade {

    void createOrganization(OrganizationDto orgToCreate, AggregateId parentOrgId, Date date);

    void terminateParty(AggregateId partyId, Date date);

    void changeParentOfOrganization(AggregateId organizationId, AggregateId newParentId, Date date);

    void createPostUnderOrganization(PostDto postDto, AggregateId organizationId, Date date);

    PostDto getPost(AggregateId postId);
    
    void setPost(Post post);

}
