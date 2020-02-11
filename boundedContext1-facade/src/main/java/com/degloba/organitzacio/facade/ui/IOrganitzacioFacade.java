package com.degloba.organitzacio.facade.ui;

import java.util.Date;

import com.degloba.organitzacio.facade.dtos.OrganitzacioDto;
import com.degloba.organitzacio.facade.dtos.PostDto;
import com.degloba.persistence.rdbms.jpa.AggregateId;


/**
 * @author degloba
 * 
 */
public interface IOrganitzacioFacade {

    void creaOrganitzacio(OrganitzacioDto orgToCreate, AggregateId parentOrgId, Date date);

    void terminateParty(AggregateId partyId, Date date);

    void changeParentOfOrganization(AggregateId organizationId, AggregateId newParentId, Date date);

    void createPostUnderOrganization(PostDto postDto, AggregateId organizationId, Date date);

    PostDto getPost(AggregateId postId);
    
    void setPost(PostDto post);

}
